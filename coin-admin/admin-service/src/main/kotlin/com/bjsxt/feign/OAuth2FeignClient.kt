package com.bjsxt.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "authorization-server")
interface OAuth2FeignClient {
    @PostMapping("/oauth/token")
    fun getToken(
        @RequestParam("grant_type") grantType: String?,  // 授权类型
        @RequestParam("username") username: String?,  // 用户名
        @RequestParam("password") password: String?,  // 用户的密码
        @RequestParam("login_type") loginType: String?,  // 登录的类型
        @RequestHeader("Authorization") basicToken: String? // Basic Y29pbi1hcGk6Y29pbi1zZWNyZXQ= 由第三方客户端信息加密出现的值
    ): ResponseEntity<JwtToken?>?
}