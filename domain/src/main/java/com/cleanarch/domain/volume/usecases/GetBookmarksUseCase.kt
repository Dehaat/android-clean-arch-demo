package com.cleanarch.domain.volume.usecases

import com.cleanarch.domain.volume.repo.IBooksRepository
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(private val booksRepository: IBooksRepository) {
    suspend operator fun invoke() = booksRepository.getBookmarks()
}