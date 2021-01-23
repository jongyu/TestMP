package com.auium.domain

data class User(
    var id: Long = 0,
    var username: String = "",
    var password: String = "",
    var roles: MutableList<Role> = mutableListOf()
) {
}