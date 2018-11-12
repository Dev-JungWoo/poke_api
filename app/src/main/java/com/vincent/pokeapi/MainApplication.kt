package com.vincent.pokeapi

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import com.vincent.pokeapi.di.DaggerAppComponent
import com.vincent.pokeapi.services.RetrofitPokeService
import com.vincent.pokeapi.services.ServiceModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MainApplication : Application() , HasActivityInjector {
    val TAG = javaClass.simpleName

    init {
        Log.d(TAG, "MainApplication initialized")
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        DaggerAppComponent
                .builder()
                .application(this)
                .serviceModule(ServiceModule(RetrofitPokeService.BASE_URL))
                .build()
                .inject(this)
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}