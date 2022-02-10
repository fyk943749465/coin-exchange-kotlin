package com.bjsxt.aspect

import cn.hutool.core.util.StrUtil
import cn.hutool.core.util.URLUtil
import com.alibaba.fastjson.JSON
import com.bjsxt.model.WebLog
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.core.LocalVariableTableParameterNameDiscoverer
import org.springframework.core.annotation.Order
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.lang.reflect.Method

@Aspect
@Order(1)
@Component
class WebLogAspect {

    private val logger = KotlinLogging.logger {}

    @Pointcut("execution( * com.bjsxt.controller.*.*(..))")
    fun webLog() {
    }

    @Around(value = "webLog()")
    @Throws(Throwable::class)
    fun recodeWebLog(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        var result: Any? = null
        val webLog = WebLog()
        val start = System.currentTimeMillis()
        result = proceedingJoinPoint.proceed(proceedingJoinPoint.args)
        val end = System.currentTimeMillis()
        webLog.spendTime = (start - end).toInt() / 1000

        // 获取当前请求的request对象
        val requestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = requestAttributes.request

        // 获取安全的上下文
        val authentication = SecurityContextHolder.getContext().authentication
        val url = request.requestURL.toString()
        webLog.uri = request.requestURI // 设置请求的uri
        webLog.url = url
        webLog.basePath = StrUtil.removeSuffix(url, URLUtil.url(url).path) // http://ip:port/
        webLog.username = authentication?.principal?.toString() ?: "anonymous" // 获取用户的id
        webLog.ip = request.remoteAddr // TODO 获取ip 地址


        // 获取方法
        val signature = proceedingJoinPoint.signature as MethodSignature
        // 获取类的名称
        val targetClassName = proceedingJoinPoint.target.javaClass.name
        val method = signature.method
        // 因为我们会使用Swagger 这工具，我们必须在方法上面添加@ApiOperation(value="")该注解
        // 获取ApiOperation
        val annotation = method.getAnnotation(ApiOperation::class.java)
        webLog.description = if (annotation == null) "no desc" else annotation.value
        webLog.method = targetClassName + "." + method.name // com.bjsxt.controller.UserController.login()
        webLog.parameter = getMethodParameter(method, proceedingJoinPoint.args) //{"key_参数的名称":"value_参数的值"}
        webLog.result = result

        // JSON.toJSONString(webLog, true)
        // logger.info(webLog.toString())
        return result
    }

    /**
     * 获取方法的执行参数
     * @param method
     * @param args
     * @return
     * {"key_参数的名称":"value_参数的值"}
     */
    private fun getMethodParameter(method: Method, args: Array<Any>): Any {
        val methodParametersWithValues: MutableMap<String, Any> = HashMap()
        val localVariableTableParameterNameDiscoverer = LocalVariableTableParameterNameDiscoverer()
        // 方法的形参名称
        val parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method)
        for (i in parameterNames.indices) {
            if (parameterNames[i] == "password" || parameterNames[i] == "file") {
                methodParametersWithValues[parameterNames[i]] = "受限的支持类型"
            } else {
                methodParametersWithValues[parameterNames[i]] = args[i]
            }
        }
        return methodParametersWithValues
    }
}
