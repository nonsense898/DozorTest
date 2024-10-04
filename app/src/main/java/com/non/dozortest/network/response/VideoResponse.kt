package com.non.dozortest.network.response

import com.non.dozortest.data.entities.Video

data class VideoResponse(
    val id: Int,
    val results: List<Video>
)
