package com.brenosiqueira.base.email.controller

import com.brenosiqueira.base.email.commons.ErrorCode
import com.brenosiqueira.base.email.commons.exception.EmailNotFoundException
import com.brenosiqueira.base.email.commons.exception.NotAuthorizeException
import com.brenosiqueira.base.email.model.boundary.BaseResponse
import org.apache.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
open class ExceptionController() {
    val log = Logger.getInstance(ExceptionController::class.toString())

    @ExceptionHandler(NotAuthorizeException::class)
    fun notAuthorize(e: NotAuthorizeException): ResponseEntity<BaseResponse> {
        log.info("nao autorizado: " + e)
        return ResponseEntity(BaseResponse(false, ErrorCode.Companion.SERVICE_NOT_FOUND), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(EmailNotFoundException::class)
    fun emailNotFound(e: EmailNotFoundException): ResponseEntity<BaseResponse> {
        log.info("Email nao encontrado: " + e)
        return ResponseEntity(BaseResponse(false, ErrorCode.Companion.NOT_FOUND), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun genericException(e: Exception): ResponseEntity<BaseResponse> {
        log.error("Erro interno " + e, e)
        return ResponseEntity(BaseResponse(false, ErrorCode.Companion.INTERNAL_ERROR), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}