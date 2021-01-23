package com.auium.rest.controller

import com.auium.rest.request.LoginRequest
import com.auium.rest.request.RegisterRequest
import com.auium.rest.response.ApiResult
import com.auium.rest.response.success
import com.auium.security.jwt.JWTFilter
import com.auium.security.jwt.TokenProvider
import com.auium.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("auth")
class AuthController(
    private val userService: UserService,
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
) {

    @PostMapping("login")
    fun login(@RequestBody @Valid request: LoginRequest): ResponseEntity<ApiResult> {
        val authenticationToken = UsernamePasswordAuthenticationToken(request.username, request.password)
        val authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)
        val jwtToken = tokenProvider.createToken(authentication, request.rememberMe)
        val httpHeaders = HttpHeaders()
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer $jwtToken")
        return ResponseEntity(success(mapOf("token" to jwtToken)), httpHeaders, HttpStatus.OK)
    }

    @PostMapping("register")
    fun register(@RequestBody @Valid request: RegisterRequest) = userService.register(request)

}