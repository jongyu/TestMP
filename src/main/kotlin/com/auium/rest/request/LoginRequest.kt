package com.auium.rest.request

import javax.validation.constraints.NotBlank

class LoginRequest {

    @NotBlank
    var username: String? = null

    @NotBlank
    var password: String? = null

    var rememberMe: Boolean = false

}