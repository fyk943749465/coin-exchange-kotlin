package com.bjsxt.config.swagger

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "swagger2")
open class SwaggerProperties {
    var basePackage: String? = null
    var name: String? = null
    var url: String? = null
    var email: String? = null
    var title: String? = null
    var description: String? = null
    var version: String? = null
    var termsOfServiceUrl: String? = null
}