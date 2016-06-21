package com.brenosiqueira.base.email.controller

import com.brenosiqueira.base.email.commons.ErrorCode
import com.brenosiqueira.base.email.model.boundary.*
import com.brenosiqueira.base.email.repository.ClientServiceRepository
import com.brenosiqueira.base.email.repository.EmailAddressRepository
import com.brenosiqueira.base.email.service.EmailAddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/base/email/v1")
open class EmailBaseController @Autowired constructor(val emailAddressService: EmailAddressService) {

    @RequestMapping(value = "/{service}", method = arrayOf(RequestMethod.POST), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun create(@PathVariable("service") service: String, @RequestHeader("password") password: String, @RequestBody request: CreateEmailAddressRequest): ResponseEntity<CreateEmailAddressResponse> {
        val response = emailAddressService.create(service, password, request)

        var status: HttpStatus
        when (response.errorCode) {
            "" -> status = HttpStatus.CREATED
            ErrorCode.EMAIL_ALREADY_CREATED -> status = HttpStatus.GONE
            else -> status = HttpStatus.INTERNAL_SERVER_ERROR
        }
        return ResponseEntity(response, status)
    }

    @RequestMapping(value = "/{service}/key/{key}", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun findEmail(@PathVariable("service") service: String, @RequestHeader("password") password: String, @PathVariable key: String): ResponseEntity<EmailAddressResponse> {
        val response = emailAddressService.findEmail(service, password, key)

        return ResponseEntity(response, if (response.errorCode == "") HttpStatus.OK else HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @RequestMapping(value = "/{service}/key/{key}", method = arrayOf(RequestMethod.DELETE), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun deleteEmail(@PathVariable("service") service: String, @RequestHeader("password") password: String, @PathVariable key: String): ResponseEntity<BaseResponse> {
        val response = emailAddressService.deleteEmail(service, password, key)
        return ResponseEntity(response, if (response.errorCode == "") HttpStatus.NO_CONTENT else HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @RequestMapping(value = "/{service}/key/{key}", method = arrayOf(RequestMethod.PUT), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun updateEmail(@PathVariable("service") service: String, @RequestHeader("password") password: String, @PathVariable key: String, @RequestBody request: UpdateEmailAddressRequest): ResponseEntity<BaseResponse> {
        val response = emailAddressService.updateEmail(service, password, key, request)
        return ResponseEntity(response, if (response.errorCode == "") HttpStatus.NO_CONTENT else HttpStatus.INTERNAL_SERVER_ERROR)
    }
}