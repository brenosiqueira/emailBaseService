package com.brenosiqueira.base.email

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

//@ComponentScan("com.brenosiqueira")
//@EnableAutoConfiguration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = arrayOf("com.brenosiqueira.base.email.repository"))
//@SpringBootApplication
@SpringBootApplication
open class Application {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}