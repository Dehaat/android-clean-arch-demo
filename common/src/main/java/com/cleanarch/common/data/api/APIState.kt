package com.cleanarch.common.data.api

import com.cleanarch.common.data.IData

sealed class APIState<out D>: IData {
    object PreExecute : APIState<Nothing>()
    sealed class PostExecute<out D> : APIState<D>()
}