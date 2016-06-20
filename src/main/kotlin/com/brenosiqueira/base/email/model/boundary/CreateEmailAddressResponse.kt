package com.brenosiqueira.base.email.model.boundary

class CreateEmailAddressResponse(success : Boolean, code: String, var uuid : String) : BaseResponse (success, code)