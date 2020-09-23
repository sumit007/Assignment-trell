package com.techsummit.trellassignmet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class VideosViewModel(private val videosRepository: VideosRepository) : ViewModel() {
    private val videos = MutableLiveData<VideoResult<List<Video>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchVideos()
    }

    private fun fetchVideos() {
        compositeDisposable.add(
            videosRepository.getDeviceVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    videos.postValue(Success(it))
                }, {
                    videos.postValue(Error("Something went wrong"))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getVideos(): LiveData<VideoResult<List<Video>>> = videos

    fun updateBookmark(videoId: String) {
        videosRepository.updateBookmark(videoId)
    }

}