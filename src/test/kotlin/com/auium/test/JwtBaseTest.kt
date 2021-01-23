package com.auium.test

import cn.hutool.core.codec.Base64
import org.junit.jupiter.api.Test
import org.springframework.util.Base64Utils

class JwtBaseTest {

    @Test
    fun genTest() {
        // test-mp-b99e118c-eead-47ff-91b2-9f1aad399c09
        val base64 = Base64Utils.encodeToString("test-mp-b99e118c-eead-47ff-91b2-9f1aad399c09".toByteArray())
        println(base64)
        println(Base64.decodeStr("dGVzdC1tcC1iOTllMTE4Yy1lZWFkLTQ3ZmYtOTFiMi05ZjFhYWQzOTljMDk="))
    }

}