package com.brenosiqueira.base.email.service

import com.brenosiqueira.base.email.commons.ErrorCode
import com.brenosiqueira.base.email.commons.exception.EmailNotFoundException
import com.brenosiqueira.base.email.commons.exception.NotAuthorizeException
import com.brenosiqueira.base.email.model.boundary.*
import com.brenosiqueira.base.email.model.entity.ClientService
import com.brenosiqueira.base.email.model.entity.EmailAddress
import com.brenosiqueira.base.email.repository.ClientServiceRepository
import com.brenosiqueira.base.email.repository.EmailAddressRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class EmailAddressService @Autowired constructor(
        val clientServiceRepository: ClientServiceRepository,
        val emailAddressRepository: EmailAddressRepository) {

    fun create(serviceName: String, servicePassword: String, request: CreateEmailAddressRequest): CreateEmailAddressResponse {
        val uuid = UUID.randomUUID().toString()
        var service = findService(serviceName, servicePassword)

        if (!containsEmailAddress(request.key, service)) {
            emailAddressRepository.save(EmailAddress(request.key, request.email, maskEmail(request.email), uuid, service))
            return CreateEmailAddressResponse(true, "", uuid)
        } else {
            return CreateEmailAddressResponse(true, ErrorCode.EMAIL_ALREADY_CREATED, "")
        }
    }

    fun findEmail(serviceName: String, servicePassword: String, key: String): EmailAddressResponse {
        val service = findService(serviceName, servicePassword)

        val emailAddress = findEmail(key, service)

        return EmailAddressResponse(true, "", emailAddress.uuid, emailAddress.key, emailAddress.email, emailAddress.mask)
    }

    fun deleteEmail(serviceName: String, servicePassword: String, key: String): BaseResponse {
        val service = findService(serviceName, servicePassword)
        val emailAddress = findEmail(key, service)
        emailAddressRepository.delete(emailAddress)
        return BaseResponse(true, "")
    }

    fun updateEmail(serviceName: String, servicePassword: String, key: String, request: UpdateEmailAddressRequest): BaseResponse {
        val service = findService(serviceName, servicePassword)
        val emailAddress = findEmail(key, service)
        emailAddress.email = request.email
        emailAddress.mask = maskEmail(request.email)
        emailAddressRepository.save(emailAddress)
        return BaseResponse(true, "")
    }

    private fun findService(serviceName: String, servicePassword: String): ClientService {
        var service: ClientService? = clientServiceRepository.findByNameAndPassword(serviceName, servicePassword);
        return service ?: throw NotAuthorizeException("Nao encontrou o servico passado ")
    }

    private fun containsEmailAddress(key: String, service: ClientService): Boolean {
        var email: EmailAddress? = emailAddressRepository.findByKeyAndClientService(key, service)
        return if (email != null) true else false
    }

    private fun findEmail(key: String, service: ClientService): EmailAddress {
        var email: EmailAddress? = emailAddressRepository.findByKeyAndClientService(key, service)
        return email ?: throw EmailNotFoundException("Nao encontrou email para " + key)
    }

    private fun maskEmail(email: String): String {
        return "${email.substring(0..1)}***${email.substring(email.indexOf('@'))}"
    }
}