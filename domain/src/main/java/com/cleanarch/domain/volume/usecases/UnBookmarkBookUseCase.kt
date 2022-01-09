package com.cleanarch.domain.volume.usecases

import com.cleanarch.domain.volume.repo.IBooksRepository
import com.cleanarch.entities.volume.VolumeEntity
import javax.inject.Inject

class UnBookmarkBookUseCase @Inject constructor(private val booksRepository: IBooksRepository) {
    suspend operator fun invoke(book: VolumeEntity) = booksRepository.unBookmark(book)
}