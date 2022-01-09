package com.cleanarch.data.book.local.database.db

import androidx.room.*
import com.cleanarch.data.book.local.database.models.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBook(book: BookEntity)

    @Query("SELECT * FROM book")
    fun getSavedBooks(): Flow<List<BookEntity>>

    @Delete
    suspend fun deleteBook(book: BookEntity)
}