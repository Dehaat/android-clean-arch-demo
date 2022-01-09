package com.cleanarch.domain.volume.repo

import com.cleanarch.common.entity.result.api.APIResultEntity
import com.cleanarch.entities.volume.VolumeEntity
import kotlinx.coroutines.flow.Flow

interface IBooksRepository {

    suspend fun getRemoteBooks(author: String): APIResultEntity<List<VolumeEntity>>

    suspend fun getBookmarks(): Flow<List<VolumeEntity>>

    suspend fun bookmark(book: VolumeEntity)

    suspend fun unBookmark(book: VolumeEntity)
}