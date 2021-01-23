package com.auium.security

import com.auium.dao.UserMapper
import com.auium.domain.User
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.core.userdetails.User as SecurityUser

@Component("userDetailsService")
class DomainUserDetailsService(private val userMapper: UserMapper) : UserDetailsService {

    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        log.debug("Authenticating {}", username)
        return userMapper
            .findByUsername(username)
            .map { createSpringSecurityUser(it) }
            .orElseThrow {UsernameNotFoundException("User $username was not found in the database")}
    }

    private fun createSpringSecurityUser(user: User): SecurityUser {
        val grantedAuthorities = user.roles.map { role -> SimpleGrantedAuthority(role.name) }
        return SecurityUser(user.username, user.password, grantedAuthorities)
    }

}