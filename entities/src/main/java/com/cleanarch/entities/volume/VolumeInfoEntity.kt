package com.cleanarch.entities.volume

import com.cleanarch.common.entity.IEntity

data class VolumeInfoEntity(
    val title: String,
    val authors: List<String>,
    val imageUrl: String?
) : IEntity