package com.example.chucknorris

import android.app.Application
import com.example.chucknorris.diKoin.appModule
import com.example.chucknorris.diKoin.interactosModule
import com.example.chucknorris.diKoin.viewModelModules
import com.example.chucknorris.diKoin.webModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChuckApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initCoin()
        Fresco.initialize(this)
    }

    private fun initCoin() {
        startKoin {
            androidContext(this@ChuckApp)
            modules(listOf(appModule, webModule, viewModelModules, interactosModule))
        }
    }

}