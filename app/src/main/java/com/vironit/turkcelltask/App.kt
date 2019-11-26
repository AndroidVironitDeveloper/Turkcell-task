package com.vironit.turkcelltask

import android.app.Application
import com.vironit.turkcelltask.di.AppComponent
import com.vironit.turkcelltask.di.DaggerAppComponent

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appContext(this)
                .build()

        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}