package com.bjsxt.model

import com.bjsxt.constant.Constants
import java.io.Serializable

class R<T> : Serializable {
    var code = 0
    var msg: String? = null
    var data: T? = null
        private set

    fun setData(data: T) {
        this.data = data
    }

    companion object {
        private const val serialVersionUID = 1L

        /**
         * 成功
         */
        val SUCCESS: Int = Constants.SUCCESS

        /**
         * 失败
         */
        val FAIL: Int = Constants.FAIL
        fun <T> ok(): R<T?> {
            return restResult(null, SUCCESS, null)
        }

        fun <T> ok(data: T): R<T> {
            return restResult(data, SUCCESS, null)
        }

        fun <T> ok(data: T, msg: String?): R<T> {
            return restResult(data, SUCCESS, msg)
        }

        fun <T> fail(): R<T?> {
            return restResult(null, FAIL, null)
        }

        fun <T> fail(msg: String?): R<T?> {
            return restResult(null, FAIL, msg)
        }


        fun <T> fail(data: T): R<T> {
            return restResult(data, FAIL, null)
        }

        fun <T> fail(data: T, msg: String?): R<T> {
            return restResult(data, FAIL, msg)
        }

        fun <T> fail(code: Int, msg: String?): R<T?> {
            return restResult(null, code, msg)
        }

        private fun <T> restResult(data: T, code: Int, msg: String?): R<T> {
            val apiResult: R<T> = R<T>()
            apiResult.code = code
            apiResult.setData(data)
            apiResult.msg = msg
            return apiResult
        }
    }
}
