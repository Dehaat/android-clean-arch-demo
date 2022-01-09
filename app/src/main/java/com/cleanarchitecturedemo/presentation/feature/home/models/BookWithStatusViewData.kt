package com.cleanarchitecturedemo.presentation.feature.home.models

data class BookWithStatusViewData(
    val id: String,
    val title: String,
    val authors: List<String>,
    val imageUrl: String?,
    val status: BookmarkStatusViewData
)