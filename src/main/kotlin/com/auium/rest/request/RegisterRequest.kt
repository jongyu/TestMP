package com.auium.rest.request

import javax.validation.constraints.NotBlank

class RegisterRequest {

    @NotBlank
    var username: String? = null

    @NotBlank
    var password: String? = null

}