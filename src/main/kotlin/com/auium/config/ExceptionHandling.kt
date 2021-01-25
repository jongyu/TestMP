package com.auium.config

import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.Status
import org.zalando.problem.spring.web.advice.ProblemHandling
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait

@ControllerAdvice
class ExceptionHandling : ProblemHandling, SecurityAdviceTrait {

    override fun handleAuthentication(ex: AuthenticationException, request: NativeWebRequest): ResponseEntity<Problem> {
        val problem: Problem = Problem.builder()
            .withStatus(Status.OK)
            .withTitle("Authentication failed")
            .withDetail(ex.localizedMessage).build()
        return create(ex, problem, request)
    }

}