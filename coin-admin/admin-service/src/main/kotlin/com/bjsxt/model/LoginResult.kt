package com.bjsxt.model

import com.bjsxt.domain.SysMenu
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.security.core.authority.SimpleGrantedAuthority


/**
 * 登录成功的结果
 */
@ApiModel(value = "登录的结果")
class LoginResult {
    /**
     * 登录成功的token，来自我们的authorization-server 里面的
     */
    @ApiModelProperty(value = "登录成功的token，来自我们的authorization-server 里面的")
    private var token: String? = null

    /**
     * 该用户的菜单数据
     */
    @ApiModelProperty(value = "该用户的菜单数据")
    private var menus: List<SysMenu>? = null

    /**
     * 该用户的权限数据
     */
    @ApiModelProperty(value = "该用户的权限数据")
    private var authorities: List<SimpleGrantedAuthority>? = null

    constructor() {}
    constructor(token: String?, menus: List<SysMenu>?, authorities: List<SimpleGrantedAuthority>?) {
        this.token = token
        this.menus = menus
        this.authorities = authorities
    }
}