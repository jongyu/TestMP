package com.auium.service.impl

import cn.hutool.core.lang.Snowflake
import com.auium.dao.RoleMapper
import com.auium.dao.UserMapper
import com.auium.domain.User
import com.auium.rest.request.RegisterRequest
import com.auium.rest.response.ApiResult
import com.auium.rest.response.failure
import com.auium.rest.response.success
import com.auium.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val snowflake: Snowflake,
    private val userMapper: UserMapper,
    private val roleMapper: RoleMapper,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    @Transactional
    override fun register(request: RegisterRequest): ApiResult {
        val roles = roleMapper.findByNameIn(mutableListOf("ROLE_USER"))
        val user = User(snowflake.nextId(), "${request.username}", passwordEncoder.encode(request.password), roles)
        val result = userMapper.register(user)
        userMapper.addRoles(user, roles)
        return if (result != 0) success() else failure("用户注册失败！")
    }

}