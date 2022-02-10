package com.bjsxt.filter

import com.alibaba.fastjson.JSONObject
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class TokenCheckFilter : GlobalFilter, Ordered {

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    @Value("\${no.token.access.urls:/admin/login}")
    lateinit var noTokenAccessUrls: Set<String>

    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void?>? {
        if (allowNoTokenAccess(exchange!!)) {
            return chain!!.filter(exchange)
        }

        val token = getToken(exchange)

        if (StringUtils.isEmpty(token)) {
            return buildUNAuthorizationResult(exchange)
        }

        val hasKey = redisTemplate.hasKey(token)
        return if (hasKey != null && hasKey) {
            chain!!.filter(exchange)
        } else buildUNAuthorizationResult(exchange)
    }

    private fun buildUNAuthorizationResult(exchange: ServerWebExchange): Mono<Void?>? {
        val response = exchange.response
        response.statusCode = HttpStatus.UNAUTHORIZED
        response.headers["Content-Type"] = "application/json;charset=UTF-8"
        val jsonObject = JSONObject()
        jsonObject["error"] = "unauthorized"
        jsonObject["error_description"] = "invalid_token"
        val dataBuffer = response.bufferFactory().wrap(jsonObject.toJSONString().toByteArray(StandardCharsets.UTF_8))
        return response.writeWith(Flux.just(dataBuffer))
    }

    private fun getToken(exchange: ServerWebExchange): String? {
        val request = exchange.request
        val headers = request.headers
        val authorization = headers.getFirst(HttpHeaders.AUTHORIZATION)
        return if (Objects.isNull(authorization) || authorization.trim { it <= ' ' }.isEmpty()
            || authorization.replace("bearer ", "").isEmpty()
        ) {
            null
        } else authorization.replace("bearer ", "")
    }

    private fun allowNoTokenAccess(exchange: ServerWebExchange): Boolean {
        val path = exchange.request.uri.path
        return noTokenAccessUrls.contains(path)
    }

    override fun getOrder(): Int {
        return 0
    }
}