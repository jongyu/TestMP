package com.auium.service

import com.auium.rest.request.RegisterRequest
import com.auium.rest.response.ApiResult

interface UserService {

    fun register(request: RegisterRequest): ApiResult

}