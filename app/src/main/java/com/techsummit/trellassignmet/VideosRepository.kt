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

        for (file in dir.listFiles()) {
            if (file.isDirectory)
                findVideos(file, list)
            else if (file.absolutePath.contains(".mp4")) {
                val fileId = getFileId(file)
                list.add(Video(getFileId(file), file.absolutePath, file.name))
            }
        }
    }

    private fun getFileId(file: File): String {
        return file.nameWithoutExtension.replace(" ", "")
    }
}