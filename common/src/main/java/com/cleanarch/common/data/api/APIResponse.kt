package com.cleanarch.common.data.api

import com.cleanarch.common.data.IData

sealed class APIResponse<out T> : APIState.PostExecute<T>(), IData {
    data class Success<D>(val data: D?) : APIResponse<D>()
    sealed class Failure : APIResponse<Nothing>() {
        data class ResponseUnsuccessful(val error: DataResponseUnsuccessful) : Failure()
        data class APIException(val error: Exception) : Failure()
    }
}

