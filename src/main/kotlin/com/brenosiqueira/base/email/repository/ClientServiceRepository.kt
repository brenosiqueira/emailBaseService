package com.brenosiqueira.base.email.repository

import com.brenosiqueira.base.email.model.entity.ClientService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface ClientServiceRepository : CrudRepository<ClientService, Long> {

    fun findByNameAndPassword(name: String, password: String): ClientService
}