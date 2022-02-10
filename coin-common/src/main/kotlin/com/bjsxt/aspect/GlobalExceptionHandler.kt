package com.bjsxt.aspect

import com.baomidou.mybatisplus.extension.exceptions.ApiException
import com.bjsxt.model.R
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    /**
     * 1 内部API调用的异常处理
     */
    @ExceptionHandler(value = [ApiException::class])
    fun <T> handlerApiException(exception: ApiException): R<Any?>{
        val errorCode = exception.errorCode
        return if (errorCode != null) {
            R.fail(errorCode)
        } else R.fail(exception.message)
    }

    /**
     * 2 方法参数校验失败的异常
     * MethodArgumentNotValidException
     *
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun <T> handlerMethodArgumentNotValidException(exception: MethodArgumentNotValidException): R<T?> {
        val bindingResult = exception.bindingResult
        if (bindingResult.hasErrors()) {
            val fieldError = bindingResult.fieldError
            if (fieldError != null) {
                return R.fail(fieldError.field + fieldError.defaultMessage)
            }
        }
        return R.fail(exception.message)
    }

    /**
     * 3 对象内部使用Validate 没有校验成功的异常
     */
    @ExceptionHandler(BindException::class)
    fun <T> handlerBindException(bindException: BindException): R<T?> {
        val bindingResult = bindException.bindingResult
        if (bindingResult.hasErrors()) {
            val fieldError = bindingResult.fieldError
            if (fieldError != null) {
                return R.fail(fieldError.field + fieldError.defaultMessage)
            }
        }
        return R.fail(bindException.message)
    }
}
