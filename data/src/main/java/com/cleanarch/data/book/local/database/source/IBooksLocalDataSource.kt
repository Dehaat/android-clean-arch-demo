package com.cleanarch.data.book.local.database.source

import com.cleanarch.entities.volume.VolumeEntity
import kotlinx.coroutines.flow.Flow

interface IBooksLocalDataSource {
    suspend fun bookmark(book: VolumeEntity)
    suspend fun unBookmark(book: VolumeEntity)
    suspend fun getBookmarks(): Flow<List<VolumeEntity>>
}