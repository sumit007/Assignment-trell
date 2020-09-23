package com.techsummit.trellassignmet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VideosViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideosViewModel::class.java)) {
            return VideosViewModel(VideosRepository()) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}