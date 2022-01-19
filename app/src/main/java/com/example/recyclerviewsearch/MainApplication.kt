package com.example.recyclerviewsearch

import android.app.Application
import java.io.InputStream

class MainApplication: Application() {
    private lateinit var colorData:InputStream
    val repository by lazy { MainRepository(colorData) }

    override fun onCreate() {
        super.onCreate()
        colorData = resources.openRawResource(R.raw.colors)
    }

}