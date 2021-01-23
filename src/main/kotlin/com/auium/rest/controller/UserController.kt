package com.auium.rest.controller

import com.auium.rest.response.success
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController {

    @GetMapping("current")
    fun currentUser() = success()

    @Secured("ROLE_ADMIN")
    @GetMapping("admin")
    fun admin() = success()

}