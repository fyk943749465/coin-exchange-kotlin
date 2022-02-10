package com.bjsxt.constant

object Constants {
    /**
     * UTF-8 字符集
     */
    const val UTF8 = "UTF-8"

    /**
     * GBK 字符集
     */
    const val GBK = "GBK"

    /**
     * http请求
     */
    const val HTTP = "http://"

    /**
     * https请求
     */
    const val HTTPS = "https://"

    /**
     * 成功标记
     */
    const val SUCCESS = 200

    /**
     * 失败标记
     */
    const val FAIL = 500

    /**
     * 验证码 redis key
     */
    const val CAPTCHA_CODE_KEY = "captcha_codes:"

    /**
     * 验证码有效期（分钟）
     */
    const val CAPTCHA_EXPIRATION: Long = 2
}
