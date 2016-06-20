package com.brenosiqueira.base.email.model.boundary

/**
 * Created by breno.siqueira on 16/06/2016.
 */
class EmailAddressResponse (success : Boolean, code: String,
                            var uuid : String,
                            var key: String,
                            var email: String,
                            var mask: String) : BaseResponse (success, code) {
}