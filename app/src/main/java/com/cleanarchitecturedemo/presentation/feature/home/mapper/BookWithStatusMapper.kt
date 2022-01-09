package com.cleanarchitecturedemo.presentation.feature.home.mapper

import com.cleanarch.entities.volume.VolumeEntity
import com.cleanarch.entities.volume.VolumeInfoEntity
import com.cleanarchitecturedemo.presentation.feature.home.models.BookWithStatusViewData
import com.cleanarchitecturedemo.presentation.feature.home.models.BookmarkStatusViewData
import javax.inject.Inject

class BookWithStatusMapper  @Inject constructor (){
    fun fromVolumeToBookWithStatus(
        volumes: List<VolumeEntity>,
        bookmarks: List<VolumeEntity>
    ): List<BookWithStatusViewData> {
        val commonElements = volumes.filter { it.id in bookmarks.map { bookmark -> bookmark.id } }
        val booksWithStatus = arrayListOf<BookWithStatusViewData>()
        for (volume in volumes) {
            if (volume in commonElements) {
                booksWithStatus.add(
                    BookWithStatusViewData(
                        volume.id,
                        volume.volumeInfo.title,
                        volume.volumeInfo.authors,
                        volume.volumeInfo.imageUrl,
                        BookmarkStatusViewData.BOOKMARKED
                    )
                )
            } else {
                booksWithStatus.add(
                    BookWithStatusViewData(
                        volume.id,
                        volume.volumeInfo.title,
                        volume.volumeInfo.authors,
                        volume.volumeInfo.imageUrl,
                        BookmarkStatusViewData.UNBOOKMARKED
                    )
                )
            }
        }
        return booksWithStatus.sortedBy { it.id }
    }

    fun fromBookWithStatusToVolume(bookWithStatus: BookWithStatusViewData): VolumeEntity {
        return VolumeEntity(
            bookWithStatus.id,
            VolumeInfoEntity(
                bookWithStatus.title,
                bookWithStatus.authors,
                bookWithStatus.imageUrl
            )
        )
    }
}