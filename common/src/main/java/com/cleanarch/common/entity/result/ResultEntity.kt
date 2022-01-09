package com.cleanarch.common.entity.result

sealed class ResultEntity<out R> : IResultEntity {
    data class Success<out D>(val data: D) : ResultEntity<D>()
    data class Failure(val error: String?, val exception: Exception?) : ResultEntity<Nothing>()
}