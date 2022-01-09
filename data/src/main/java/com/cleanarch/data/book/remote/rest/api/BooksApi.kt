package com.cleanarch.data.book.remote.rest.api

import com.cleanarch.data.book.remote.BooksApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("books/v1/volumes")
    suspend fun getBooks(@Query("q") author: String): Response<BooksApiResponse>
}