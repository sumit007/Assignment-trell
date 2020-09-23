package com.techsummit.trellassignmet

import android.os.Environment
import io.reactivex.Single
import java.io.File

class VideosRepository {
    private val listVideos = mutableListOf<Video>()
    fun getDeviceVideos(): Single<List<Video>> {
        findVideos(Environment.getExternalStorageDirectory(), listVideos)
        return Single.just(listVideos)
    }

    private fun findVideos(dir: File, list: MutableList<Video>) {
        val bookmarkedVideos = BookmarkHelper.getListString()

        for (file in dir.listFiles()) {
            if (file.isDirectory)
                findVideos(file, list)
            else if (file.absolutePath.contains(".mp4")) {
                val fileId = getFileId(file)
                list.add(Video(
                    id = getFileId(file),
                    uri = file.absolutePath,
                    title = file.name,
                    isBookmarked = bookmarkedVideos.contains(fileId)
                ))
            }
        }
    }

    private fun getFileId(file: File): String {
        return file.nameWithoutExtension.replace(" ", "")
    }

    fun updateBookmark(videoId: String) {
        val bookmarkedVideos = BookmarkHelper.getListString()
        if (bookmarkedVideos.contains(videoId)) {
            bookmarkedVideos.remove(videoId)
        } else {
            bookmarkedVideos.add(videoId)
        }

        BookmarkHelper.saveListString(bookmarkedVideos)
    }
}