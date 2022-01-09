package com.cleanarch.common.entity.result.api

import com.cleanarch.common.entity.result.IResultEntity

sealed class APIResultEntity<out R> : IResultEntity {
    data class Success<out T>(val data: T) : APIResultEntity<T>()
    sealed class Failure : APIResultEntity<Nothing>() {
        data class ErrorException(val exceptionError: Exception) : Failure()
        data class ErrorFailure(
            val httpCode: Int,
            val responseMessage: String,
            val responseErrorBody: String?,
        ) : Failure()
    }
}
