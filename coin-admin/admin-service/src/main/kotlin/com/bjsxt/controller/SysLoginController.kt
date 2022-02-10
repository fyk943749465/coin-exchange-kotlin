package com.bjsxt.controller

import com.bjsxt.model.LoginResult
import com.bjsxt.service.SysLoginService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@Api(tags = ["登录的控制器"])
open class SysLoginController {

    @Autowired
    open lateinit var loginService: SysLoginService

    @PostMapping("/login")
    @ApiOperation(value = "后台管理人员登录")
    @ApiImplicitParams(
        ApiImplicitParam(name = "username", value = "用户名称"),
        ApiImplicitParam(name = "password", value = "用户的密码")
    )
    fun login(
        @RequestParam(required = true) username: String?,  // 用户名称
        @RequestParam(required = true) password: String? // 用户的密码
    ): LoginResult? {
        return loginService?.login(username, password)
    }
}