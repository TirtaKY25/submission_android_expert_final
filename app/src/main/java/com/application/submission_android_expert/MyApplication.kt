package com.application.submission_android_expert

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.application.submission_android_expert.core.data.source.local.preferences.SettingsPreferences
import com.application.submission_android_expert.core.di.dbModule
import com.application.submission_android_expert.core.di.networkModule
import com.application.submission_android_expert.core.di.prefModule
import com.application.submission_android_expert.core.di.repositoryModule
import com.application.submission_android_expert.di.useCaseModule
import com.application.submission_android_expert.di.viewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    dbModule,
                    prefModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }

        val pref: SettingsPreferences = get()
        CoroutineScope(Dispatchers.IO).launch {
            val isDarkMode = pref.getThemeSettings().first()
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}