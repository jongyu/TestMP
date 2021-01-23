package com.auium.dao

import com.auium.domain.Role
import com.auium.domain.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.util.*

@Mapper
@Repository
interface UserMapper {

    fun findByUsername(@Param("username") username: String): Optional<User>

    fun register(user: User): Int

    fun addRoles(@Param("user") user: User, @Param("roles") roles: MutableList<Role>): Int

}