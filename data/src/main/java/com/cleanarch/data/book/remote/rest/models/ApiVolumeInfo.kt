package com.cleanarch.data.book.remote.rest.models

data class ApiVolumeInfo(
    val title: String,
    val authors: List<String>,
    val imageLinks: ImageLinks?
)