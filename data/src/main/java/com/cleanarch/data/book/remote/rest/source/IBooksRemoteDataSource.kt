package com.cleanarch.data.book.remote.rest.source

import com.cleanarch.base.entity.result.api.APIResultEntity
import com.cleanarch.entities.volume.VolumeEntity

interface IBooksRemoteDataSource {
    suspend fun getBooks(author: String): APIResultEntity<List<VolumeEntity>>
}