package com.cleanarchitecturedemo.core

import android.app.Application
import android.content.Intent
import com.cleanarchitecturedemo.presentation.feature.home.ui.MainActivity
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CleanArchitectureDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}