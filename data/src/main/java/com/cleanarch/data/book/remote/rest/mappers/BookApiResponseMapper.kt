package com.cleanarch.data.book.remote.rest.mappers

import com.cleanarch.data.book.remote.BooksApiResponse
import com.cleanarch.entities.volume.VolumeEntity
import com.cleanarch.entities.volume.VolumeInfoEntity
import javax.inject.Inject

class BookApiResponseMapper @Inject constructor() {
    fun toVolumeList(response: BooksApiResponse): List<VolumeEntity> {
        return response.items.map {
            VolumeEntity(
                it.id, VolumeInfoEntity(
                    it.volumeInfo.title,
                    it.volumeInfo.authors,
                    it.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
                )
            )
        }
    }
}