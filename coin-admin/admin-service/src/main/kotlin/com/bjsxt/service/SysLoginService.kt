package com.bjsxt.service

import com.bjsxt.model.LoginResult

/**
 * 登录的接口
 */
interface SysLoginService {
    /**
     * 登录的实现
     * @param username
     * 用户名
     * @param password
     *
     * 用户的密码
     * @return
     * 登录的结果
     */
    fun login(username: String?, password: String?): LoginResult?
}