package com.cleanarch.data.book.local.database.mapper

import com.cleanarch.data.book.local.database.models.BookEntity
import com.cleanarch.entities.volume.VolumeEntity
import com.cleanarch.entities.volume.VolumeInfoEntity
import javax.inject.Inject

class BookEntityMapper @Inject constructor() {
    fun toBookEntity(volume: VolumeEntity): BookEntity {
        return BookEntity(
            id = volume.id,
            title = volume.volumeInfo.title,
            authors = volume.volumeInfo.authors,
            imageUrl = volume.volumeInfo.imageUrl
        )
    }

    fun toVolume(bookEntity: BookEntity): VolumeEntity {
        return VolumeEntity(
            bookEntity.id,
            VolumeInfoEntity(
                bookEntity.title,
                bookEntity.authors,
                bookEntity.imageUrl
            )
        )
    }
}