package com.auium.security.jwt

import cn.hutool.core.date.DateUtil
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class TokenProvider {

    private val log = LoggerFactory.getLogger(javaClass)
    private val issuer = "shein"
    private val authKey = "auth"
    private val algorithm = Algorithm.HMAC256("test-mp-automation-test-platform")

    fun createToken(authentication: Authentication, rememberMe: Boolean): String {
        val authorities = authentication.authorities.stream().map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.joining(","))
        val validity = if (rememberMe) DateUtil.nextMonth() else DateUtil.nextWeek()
        return JWT.create()
            .withIssuer(issuer)
            .withClaim(authKey, authorities)
            .withSubject(authentication.name)
            .withIssuedAt(Date())
            .withExpiresAt(validity)
            .sign(algorithm)
    }

    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        val verifier = JWT.require(algorithm).withIssuer(issuer).build()
        val jwt = verifier.verify(token)
        val claim = jwt.getClaim(authKey).asString().split(",").toTypedArray()
        val authorities: Collection<GrantedAuthority?> = Arrays
            .stream(claim)
            .map { role: String? -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toList())
        val principal = User(jwt.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(authToken: String?): Boolean {
        try {
            if (!authToken.isNullOrBlank()) {
                val verifier = JWT.require(algorithm).withIssuer(issuer).build()
                val jwt = verifier.verify(authToken)
                return jwt.claims.containsKey("auth")
            }
        } catch (ex: JWTVerificationException) {
            log.info("Invalid JWT token.")
            log.trace("Invalid JWT token trace. $ex")
        }
        return false
    }

}