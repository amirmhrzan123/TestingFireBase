package com.example.amir.testingfirebase

import android.support.multidex.MultiDexApplication
import com.example.amir.testingfirebase.chatModules.di.appModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Amir on 10/18/2018.
 */
class FireBaseApp:MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule)

    }
}