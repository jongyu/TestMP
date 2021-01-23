package com.auium.security.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTFilter(private val tokenProvider: TokenProvider) : GenericFilterBean() {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        request as HttpServletRequest
        val jwt = resolveToken(request)
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            val authentication: Authentication = tokenProvider.getAuthentication("$jwt")
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }
}