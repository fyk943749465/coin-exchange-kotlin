package com.bjsxt.service.impl

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode
import com.baomidou.mybatisplus.extension.exceptions.ApiException
import com.bjsxt.domain.SysMenu
import com.bjsxt.feign.JwtToken
import com.bjsxt.feign.OAuth2FeignClient
import com.bjsxt.model.LoginResult
import com.bjsxt.service.SysLoginService
import com.bjsxt.service.SysMenuService
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.jwt.JwtHelper
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

@Service
open class SysLoginServiceImpl : SysLoginService {

    private val log = KotlinLogging.logger {}

    @Autowired
    lateinit var oAuth2FeignClient: OAuth2FeignClient

    @Autowired
    lateinit var sysMenuService: SysMenuService

    @Value("\${basic.token:Basic Y29pbi1hcGk6Y29pbi1zZWNyZXQ=}")
    lateinit var basicToken: String

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    /**
     * 登录的实现
     *
     * @param username 用户名
     * @param password 用户的密码
     * @return 登录的结果
     */
    override fun login(username: String?, password: String?): LoginResult? {
        log.info("用户{}开始登录", username)
        // 1 获取token 需要远程调用authorization-server 该服务
        val tokenResponseEntity: ResponseEntity<JwtToken?>? =
            oAuth2FeignClient?.getToken("password", username, password, "admin_type", basicToken)
        if (tokenResponseEntity != null) {
            if (tokenResponseEntity.getStatusCode() != HttpStatus.OK) {
                throw ApiException(ApiErrorCode.FAILED)
            }
        }
        val jwtToken: JwtToken? = tokenResponseEntity?.getBody()
        log.info("远程调用授权服务器成功,获取的token为{}", JSON.toJSONString(jwtToken, true))
        val token: String? = jwtToken?.accessToken

        // 2 查询我们的菜单数据
        val jwt = JwtHelper.decode(token)
        val jwtJsonStr = jwt.claims
        val jwtJson = JSON.parseObject(jwtJsonStr)
        val userId = java.lang.Long.valueOf(jwtJson.getString("user_name"))
        val menus: List<SysMenu> = sysMenuService?.getMenusByUserId(userId) as List<SysMenu>

        // 3 权限数据怎么查询 -- 不需要查询的，因为我们的jwt 里面已经包含了
        val authoritiesJsonArray = jwtJson.getJSONArray("authorities")
        val authorities = authoritiesJsonArray.stream() // 组装我们的权限数据
            .map { authorityJson: Any ->
                SimpleGrantedAuthority(
                    authorityJson.toString()
                )
            }
            .collect(Collectors.toList())
        // 1 将该token 存储在redis 里面，配置我们的网关做jwt验证的操作
        redisTemplate!!.opsForValue()[token, "", jwtToken?.expiresIn!!] = TimeUnit.SECONDS
        //2 我们返回给前端的Token 数据，少一个bearer：
        return LoginResult(jwtToken?.tokenType + " " + token, menus, authorities)
    }
}