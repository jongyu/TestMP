package com.auium.rest.response

data class ApiResult(var status: Int = 0, var message: String = "ok", var data: Any? = null)

fun success() = ApiResult()

fun success(data: Any?) = ApiResult(data = data)

fun failure(message: String) = ApiResult(1, message)