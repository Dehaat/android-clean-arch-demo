package com.cleanarch.domain.volume.usecases

import com.cleanarch.domain.volume.repo.IBooksRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val booksRepository: IBooksRepository) {
    suspend operator fun invoke(author: String) = booksRepository.getRemoteBooks(author)
}