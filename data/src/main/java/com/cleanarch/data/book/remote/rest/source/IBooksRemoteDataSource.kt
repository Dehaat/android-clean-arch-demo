package com.cleanarch.data.book.remote.rest.source

import com.cleanarch.common.entity.result.api.APIResultEntity
import com.cleanarch.entities.volume.VolumeEntity

interface IBooksRemoteDataSource {
    suspend fun getBooks(author: String): APIResultEntity<List<VolumeEntity>>
}