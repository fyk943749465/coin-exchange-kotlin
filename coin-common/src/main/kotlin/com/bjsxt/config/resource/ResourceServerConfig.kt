package com.bjsxt.config.resource

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.util.FileCopyUtils

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
open class ResourceServerConfig : ResourceServerConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf()
            .disable()
            .sessionManagement().disable()
            .authorizeRequests()
            .antMatchers(
                "/markets/kline/**",
                "/users/setPassword",
                "/users/register",
                "/sms/sendTo",
                "/gt/register",
                "/login",
                "/v2/api-docs",
                "/swagger-resources/configuration/ui",  //用来获取支持的动作
                "/swagger-resources",  //用来获取api-docs的URI
                "/swagger-resources/configuration/security",  //安全选项
                "/webjars/**",
                "/swagger-ui.html"
            ).permitAll()
            .antMatchers("/**").authenticated()
            .and().headers().cacheControl()
    }

    /**
     * 设置公钥
     *
     * @param resources
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.tokenStore(jwtTokenStore())
    }

    private fun jwtTokenStore(): TokenStore {
        return JwtTokenStore(accessTokenConverter())
    }

    @Bean
    open // 放在ioc容器的
    fun accessTokenConverter(): JwtAccessTokenConverter {
        //resource 验证token（公钥） authorization 产生 token （私钥）
        val tokenConverter = JwtAccessTokenConverter()
        var s: String? = null
        try {
            val classPathResource = ClassPathResource("coinexchange.txt")
            val bytes = FileCopyUtils.copyToByteArray(classPathResource.inputStream)
            s = String(bytes, Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        tokenConverter.setVerifierKey(s)
        return tokenConverter
    }
}
