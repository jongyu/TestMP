package com.auium.config

import com.auium.rest.errors.ExceptionHandling
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.zalando.problem.jackson.ProblemModule

@Configuration
@Import(ExceptionHandling::class)
class MvcConfiguration : WebMvcConfigurationSupport() {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.clear()
        converters.add(MappingJackson2HttpMessageConverter(ObjectMapper().registerModule(ProblemModule())))
    }

}