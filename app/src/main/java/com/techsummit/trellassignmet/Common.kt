package com.techsummit.trellassignmet

sealed class VideoResult<T>

data class Success<T>(val data : T) : VideoResult<T>()

data class Error<T>(val errorMessage : String) : VideoResult<T>()