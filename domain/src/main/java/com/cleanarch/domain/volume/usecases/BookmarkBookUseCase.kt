package com.cleanarch.domain.volume.usecases

import com.cleanarch.domain.volume.repo.IBooksRepository
import com.cleanarch.entities.volume.VolumeEntity
import javax.inject.Inject

class BookmarkBookUseCase @Inject constructor(private val booksRepository: IBooksRepository) {
    suspend operator fun invoke(book: VolumeEntity) = booksRepository.bookmark(book)
}