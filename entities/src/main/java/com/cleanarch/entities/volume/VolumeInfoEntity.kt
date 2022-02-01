package com.cleanarch.entities.volume

import com.cleanarch.base.entity.IEntity

data class VolumeInfoEntity(
    val title: String,
    val authors: List<String>,
    val imageUrl: String?
) : IEntity