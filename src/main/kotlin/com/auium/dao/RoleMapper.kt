package com.auium.dao

import com.auium.domain.Role
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.util.*

@Mapper
@Repository
interface RoleMapper {

    fun findByName(name: String): Optional<Role>

    fun findByNameIn(@Param("roles") name: MutableList<String>): MutableList<Role>

}