package com.brenosiqueira.base.email.model.entity

import javax.persistence.*

@Entity
@Table(name = "client_service")
class ClientService(
        @Column(name = "name")
        var name : String = "",
        @Column(name = "password")
        var password : String = "",
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long = 0
)