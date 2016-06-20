package com.brenosiqueira.base.email.commons

class ErrorCode {
    companion object {
        const val SERVICE_NOT_FOUND = "401"
        const val NOT_FOUND = "404"
        const val EMAIL_ALREADY_CREATED = "410"
        const val INTERNAL_ERROR = "500"
    }
}