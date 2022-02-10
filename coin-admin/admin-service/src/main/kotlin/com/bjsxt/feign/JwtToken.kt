package com.bjsxt.feign

import com.fasterxml.jackson.annotation.JsonProperty

class JwtToken {
    /**
     * access_token
     */
    @JsonProperty("access_token")
    val accessToken: String? = null

    /**
     * token类型
     */
    @JsonProperty("token_type")
    val tokenType: String? = null

    /**
     * refresh_token
     */
    @JsonProperty("refresh_token")
    val refreshToken: String? = null

    /**
     * 过期时间
     */
    @JsonProperty("expires_in")
    val expiresIn: Long? = null

    /**
     * token的范围
     */
    val scope: String? = null

    /**
     * 颁发的凭证
     */
    val jti: String? = null
}
