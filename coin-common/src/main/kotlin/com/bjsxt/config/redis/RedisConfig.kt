package com.bjsxt.config.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
open class RedisConfig {
    @Bean
    open fun redisTemplate(redisConnectionFactory: RedisConnectionFactory?): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory
        val stringRedisSerializer = StringRedisSerializer()
        val genericJackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer()
        redisTemplate.keySerializer = stringRedisSerializer
        redisTemplate.valueSerializer = genericJackson2JsonRedisSerializer
        redisTemplate.hashKeySerializer = stringRedisSerializer
        redisTemplate.hashValueSerializer = genericJackson2JsonRedisSerializer
        return redisTemplate
    }
}