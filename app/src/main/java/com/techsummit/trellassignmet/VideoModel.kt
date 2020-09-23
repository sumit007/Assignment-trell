package com.techsummit.trellassignmet

data class Video(
    val fileId: String,
    val uri: String,
    val isBookmarked: Boolean = false
)