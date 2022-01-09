package com.cleanarch.data.book.remote.rest.source

import com.cleanarch.common.data.api.makeAPICall
import com.cleanarch.data.book.remote.rest.api.BooksApi
import com.cleanarch.data.book.remote.rest.mappers.BookApiResponseMapper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class BooksRemoteDataSource @Inject constructor(
    private val service: BooksApi,
    private val mapper: BookApiResponseMapper
) : IBooksRemoteDataSource {
    override suspend fun getBooks(author: String) =
        makeAPICall(Dispatchers.IO, { service.getBooks(author) }) {
            it?.let { response -> mapper.toVolumeList(response) } ?: emptyList()
        }
}