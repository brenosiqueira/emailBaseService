package com.brenosiqueira.base.email.model.entity

import javax.persistence.*

@Entity
@Table(name = "email_address")
class EmailAddress(
        @Column(name = "key")
        var key: String = "",
        @Column(name = "email")
        var email: String = "",
        @Column(name = "mask")
        var mask: String = "",
        @Column(name = "uuid")
        var uuid: String = "",
        @ManyToOne
        @JoinColumn(name = "client_service_id", referencedColumnName = "id")
        var clientService: ClientService? = null,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)