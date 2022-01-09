package com.cleanarch.data.book.local.database.source

import com.cleanarch.data.book.local.database.db.BookDao
import com.cleanarch.data.book.local.database.mapper.BookEntityMapper
import com.cleanarch.entities.volume.VolumeEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksLocalDataSource @Inject constructor(
    private val bookDao: BookDao,
    private val dispatcher: CoroutineDispatcher,
    private val bookEntityMapper: BookEntityMapper
) : IBooksLocalDataSource {
    override suspend fun bookmark(book: VolumeEntity) = withContext(dispatcher) {
        bookDao.saveBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun unBookmark(book: VolumeEntity) = withContext(dispatcher) {
        bookDao.deleteBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun getBookmarks(): Flow<List<VolumeEntity>> {
        val savedBooksFlow = bookDao.getSavedBooks()
        return savedBooksFlow.map { list ->
            list.map { element -> bookEntityMapper.toVolume(element) }
        }
    }
}