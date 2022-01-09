package com.cleanarchitecturedemo.presentation.feature.home.ui

import com.cleanarchitecturedemo.presentation.feature.home.models.BookWithStatusViewData

interface ActionClickListener {
    fun bookmark(book: BookWithStatusViewData)
    fun unBookmark(book: BookWithStatusViewData)
}