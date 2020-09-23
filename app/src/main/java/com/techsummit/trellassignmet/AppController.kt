package com.techsummit.trellassignmet

import android.app.Application

class AppController: Application() {
    override fun onCreate() {
        super.onCreate()
        BookmarkHelper.init(this)
    }
}