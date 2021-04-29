package me.hechfx.interaktions.util

import com.squareup.okhttp.OkHttpClient

object HttpUtil {
    val httpClient
        get() = OkHttpClient()
}