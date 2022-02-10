package com.bjsxt.config.jackson

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.text.SimpleDateFormat
import java.util.*

@Configuration
open class JacksonConfig {
    @Bean
    open fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        val simpleModule = SimpleModule()
        simpleModule.addSerializer(Long::class.java, ToStringSerializer.instance)
        objectMapper.registerModule(simpleModule)
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))
        objectMapper.dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        return objectMapper
    }
}