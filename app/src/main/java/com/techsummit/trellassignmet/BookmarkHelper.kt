package com.techsummit.trellassignmet

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import java.util.ArrayList

object BookmarkHelper {
    private const val PREFERENCE_NAME = "video-bookmark"
    private const val KEY_BOOKMARKED_VIDEOS = "bookmarked-videos"
    private lateinit var sharedPreferences: SharedPreferences


    fun init(context: Context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun saveListString(stringList: ArrayList<String>) {
        val stringArray = stringList.toTypedArray()
        sharedPreferences.edit().putString(KEY_BOOKMARKED_VIDEOS, TextUtils.join("‚‗‚", stringArray)).apply()
    }

    fun getListString(): ArrayList<String> {
        return ArrayList(
            mutableListOf(
                *TextUtils.split(
                    sharedPreferences.getString(KEY_BOOKMARKED_VIDEOS, ""),
                    "‚‗‚"
                )
            )
        )
    }
}