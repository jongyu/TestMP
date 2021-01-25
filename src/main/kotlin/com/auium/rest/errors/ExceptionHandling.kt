package com.auium.rest.errors

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.Status
import org.zalando.problem.spring.web.advice.ProblemHandling
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait

@ControllerAdvice
class ExceptionHandling : ProblemHandling, SecurityAdviceTrait {

    override fun handleAuthentication(ex: AuthenticationException, request: NativeWebRequest): ResponseEntity<Problem> {
        val status = if (ex is UsernameNotFoundException || ex is BadCredentialsException) Status.OK else Status.UNAUTHORIZED
        val problem: Problem = Problem.builder()
            .withStatus(status)
            .withTitle("Authentication failed")
            .withDetail(ex.localizedMessage).build()
        return create(ex, problem, request)
    }

}