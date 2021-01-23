package com.auium.config

import cn.hutool.core.lang.Snowflake
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SnowflakeConfiguration {

    @Bean
    fun createSnowflake(): Snowflake {
        return Snowflake(1, 1)
    }

}