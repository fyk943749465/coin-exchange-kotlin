package com.bjsxt.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory

@EnableAuthorizationServer
@Configuration
open class AuthorizationConfig : AuthorizationServerConfigurerAdapter() {
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Qualifier("userServiceDetailsServiceImpl")
    @Autowired
    lateinit var userDetailsService: UserDetailsService

    //    @Autowired
    //    public RedisConnectionFactory redisConnectionFactory;
    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient("coin-api")
            .secret(passwordEncoder!!.encode("coin-secret"))
            .scopes("all")
            .authorizedGrantTypes("password", "refresh_token")
            .accessTokenValiditySeconds(7 * 24 * 3600)
            .refreshTokenValiditySeconds(30 * 24 * 3600)
            .and()
            .withClient("inside-app")
            .secret(passwordEncoder!!.encode("inside-secret"))
            .authorizedGrantTypes("client_credentials")
            .scopes("all")
            .accessTokenValiditySeconds(7 * 24 * 3600)
        super.configure(clients)
    }

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .tokenStore(jwtTokenStore())
            .tokenEnhancer(jwtAccessTokenConverter())
        super.configure(endpoints)
    }

    fun jwtTokenStore(): TokenStore {
        return JwtTokenStore(jwtAccessTokenConverter())
    }

    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val tokenConverter = JwtAccessTokenConverter()
        val classPathResource = ClassPathResource("coinexchange.jks")
        val keyStoreKeyFactory = KeyStoreKeyFactory(classPathResource, "coinexchange".toCharArray())
        tokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("coinexchange", "coinexchange".toCharArray()))
        return tokenConverter
    }
}