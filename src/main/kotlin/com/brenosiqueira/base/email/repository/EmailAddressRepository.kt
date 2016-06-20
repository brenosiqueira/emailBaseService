package com.brenosiqueira.base.email.repository

import com.brenosiqueira.base.email.model.entity.ClientService
import com.brenosiqueira.base.email.model.entity.EmailAddress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface EmailAddressRepository : CrudRepository<EmailAddress, Long> {

    fun findByKeyAndClientService(key : String, clientService : ClientService) : EmailAddress
}