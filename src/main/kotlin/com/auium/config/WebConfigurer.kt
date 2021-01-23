package com.auium.config

import org.slf4j.LoggerFactory
import org.springframework.boot.web.server.MimeMappings
import org.springframework.boot.web.server.WebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.nio.charset.StandardCharsets
import javax.servlet.ServletContext

@Configuration
class WebConfigurer : ServletContextInitializer, WebServerFactoryCustomizer<WebServerFactory> {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun onStartup(servletContext: ServletContext) {
        log.info("Web application fully configured")
    }

    override fun customize(server: WebServerFactory) {
        setMimeMappings(server)
    }

    private fun setMimeMappings(server: WebServerFactory) {
        if (server is ConfigurableServletWebServerFactory) {
            val mappings = MimeMappings(MimeMappings.DEFAULT)
            // IE issue, see https://github.com/jhipster/generator-jhipster/pull/711
            mappings.add("html", MediaType.TEXT_HTML_VALUE + ";charset=" + StandardCharsets.UTF_8.name().toLowerCase())
            // CloudFoundry issue, see https://github.com/cloudfoundry/gorouter/issues/64
            mappings.add("json", MediaType.TEXT_HTML_VALUE + ";charset=" + StandardCharsets.UTF_8.name().toLowerCase())
            server.setMimeMappings(mappings)
        }
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        source.registerCorsConfiguration("/management/**", config)
        source.registerCorsConfiguration("/v2/api-docs", config)
        return CorsFilter(source)
    }
}