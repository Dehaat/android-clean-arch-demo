package com.cleanarch.common.data.api

import com.cleanarch.common.data.IData
import okhttp3.ResponseBody

data class DataResponseUnsuccessful(
    val errorCode: Int,
    val errorBody: ResponseBody?,
    val errorMsgResponse: String,
): IData