package com.cleanarch.data.book.repo

import com.cleanarch.base.entity.result.api.APIResultEntity
import com.cleanarch.data.book.local.database.source.IBooksLocalDataSource
import com.cleanarch.data.book.remote.rest.source.IBooksRemoteDataSource
import com.cleanarch.domain.volume.repo.IBooksRepository
import com.cleanarch.entities.volume.VolumeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val localDataSource: IBooksLocalDataSource,
    private val remoteDataSource: IBooksRemoteDataSource
) : IBooksRepository {

    override suspend fun getRemoteBooks(author: String): APIResultEntity<List<VolumeEntity>> {
        return remoteDataSource.getBooks(author)
    }

    override suspend fun getBookmarks(): Flow<List<VolumeEntity>> {
        return localDataSource.getBookmarks()
    }

    override suspend fun bookmark(book: VolumeEntity) {
        localDataSource.bookmark(book)
    }

    override suspend fun unBookmark(book: VolumeEntity) {
        localDataSource.unBookmark(book)
    }
}