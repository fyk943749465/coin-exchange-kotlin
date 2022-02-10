package com.bjsxt.config.swagger

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties::class)
open class SwaggerAutoConfiguration(private val swaggerProperties: SwaggerProperties) {
    @Bean
    open fun docket(): Docket {
        val docket = Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(swaggerProperties.basePackage))
            .paths(PathSelectors.any())
            .build()
        docket.securitySchemes(securitySchemes())
            .securityContexts(securityContexts())
        return docket
    }

    private fun securityContexts(): List<SecurityContext> {
        return Arrays.asList(
            SecurityContext(
                Arrays.asList(
                    SecurityReference(
                        "Authorization", arrayOf(
                            AuthorizationScope("global", "accessResource")
                        )
                    )
                ),
                PathSelectors.any()
            )
        )
    }

    private fun securitySchemes(): List<SecurityScheme> {
        return Arrays.asList<SecurityScheme>(ApiKey("Authorization", "Authorization", "Authorization"))
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().contact(
            Contact(swaggerProperties.name, swaggerProperties.url, swaggerProperties.email)
        )
            .title(swaggerProperties.title)
            .description(swaggerProperties.description)
            .version(swaggerProperties.version)
            .termsOfServiceUrl(swaggerProperties.termsOfServiceUrl)
            .build()
    }
}
