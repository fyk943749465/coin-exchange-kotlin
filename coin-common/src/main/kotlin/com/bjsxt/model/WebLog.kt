package com.bjsxt.model

import java.util.*

class WebLog {
    /**
     * 操作描述
     */
    var description: String? = null

    /**
     * 操作用户
     */
    var username: String? = null

    /**
     * 消耗时间
     */
    var spendTime: Int? = null

    /**
     * 根路径
     */
    var basePath: String? = null

    /**
     * URI
     */
    var uri: String? = null

    /**
     * URL
     */
    var url: String? = null

    /**
     * 请求类型
     */
    var method: String? = null

    /**
     * IP地址
     */
    var ip: String? = null

    /**
     * 请求参数
     */
    var parameter: Any? = null

    /**
     * 返回结果
     */
    var result: Any? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val webLog = o as WebLog
        return description == webLog.description && username == webLog.username && spendTime == webLog.spendTime && basePath == webLog.basePath && uri == webLog.uri && url == webLog.url && method == webLog.method && ip == webLog.ip && parameter == webLog.parameter && result == webLog.result
    }

    override fun hashCode(): Int {
        return Objects.hash(description, username, spendTime, basePath, uri, url, method, ip, parameter, result)
    }

}

