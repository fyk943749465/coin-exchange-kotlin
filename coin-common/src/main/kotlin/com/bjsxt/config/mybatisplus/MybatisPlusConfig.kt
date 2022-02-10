package com.bjsxt.config.mybatisplus

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@MapperScan("com.bjsxt.mapper")
open class MybatisPlusConfig {
    @Bean
    open fun paginationInterceptor(): PaginationInterceptor {
        val paginationInterceptor = PaginationInterceptor()
        paginationInterceptor.setDbType(DbType.MYSQL)
        return paginationInterceptor
    }

    @Bean
    open fun optimisticLockerInterceptor(): OptimisticLockerInterceptor {
        return OptimisticLockerInterceptor()
    }

    @Bean
    open fun iKeyGenerator(): IKeyGenerator {
        return H2KeyGenerator()
    }
}