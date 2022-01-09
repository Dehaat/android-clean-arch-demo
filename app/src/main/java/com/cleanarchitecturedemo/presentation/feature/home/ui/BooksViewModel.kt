package com.cleanarchitecturedemo.presentation.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agridroid.baselib.extensions.callAndGetUIState
import com.cleanarch.common.presentation.ui.UIState
import com.cleanarch.domain.volume.usecases.BookmarkBookUseCase
import com.cleanarch.domain.volume.usecases.GetBookmarksUseCase
import com.cleanarch.domain.volume.usecases.GetBooksUseCase
import com.cleanarch.domain.volume.usecases.UnBookmarkBookUseCase
import com.cleanarch.entities.volume.VolumeEntity
import com.cleanarchitecturedemo.presentation.feature.home.mapper.BookWithStatusMapper
import com.cleanarchitecturedemo.presentation.feature.home.models.BookWithStatusViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val bookmarkBookUseCase: BookmarkBookUseCase,
    private val unBookmarkBookUseCase: UnBookmarkBookUseCase,
    private val mapper: BookWithStatusMapper
) : ViewModel() {

    private val _books = MutableStateFlow<UIState<List<BookWithStatusViewData>>>(UIState.DEFAULT)
    val books = _books.asSharedFlow()

    private val _remoteBooks = arrayListOf<VolumeEntity>()

    fun searchBooks(author: String) = viewModelScope.launch {
        callAndGetUIState({ getBooksUseCase.invoke(author) }, {
            _remoteBooks.clear()
            _remoteBooks.addAll(it)
            getBooks()
            true
        })
    }

    private fun getBooks() = viewModelScope.launch {
        getBookmarksUseCase.invoke().collect {
            _books.emit(UIState.Loaded.Success(mapper.fromVolumeToBookWithStatus(_remoteBooks, it)))
        }
    }

    fun bookmark(book: BookWithStatusViewData) {
        viewModelScope.launch {
            bookmarkBookUseCase.invoke(mapper.fromBookWithStatusToVolume(book))
        }
    }

    fun unBookmark(book: BookWithStatusViewData) {
        viewModelScope.launch {
            unBookmarkBookUseCase.invoke(mapper.fromBookWithStatusToVolume(book))
        }
    }
}