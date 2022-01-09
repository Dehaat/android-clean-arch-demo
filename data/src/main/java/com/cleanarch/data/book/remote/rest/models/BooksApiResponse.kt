package com.cleanarch.data.book.remote

import com.cleanarch.data.book.remote.rest.models.ApiVolumeInfo
import com.squareup.moshi.Json

class BooksApiResponse(val items: List<Item>)

data class Item(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "volumeInfo")
    val volumeInfo: ApiVolumeInfo
)




