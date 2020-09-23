package com.techsummit.trellassignmet

import android.icu.text.CaseMap
import com.google.android.exoplayer2.SimpleExoPlayer

data class Video(
    val id: String,
    val uri: String,
    val title: String,
    var isBookmarked: Boolean = false,
    var player: SimpleExoPlayer? = null
)