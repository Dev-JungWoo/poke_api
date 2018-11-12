package com.vincent.pokeapi.di

import android.app.Application
import com.vincent.pokeapi.MainApplication
import com.vincent.pokeapi.services.ServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//https://google.github.io/dagger/android.html
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivitiesModule::class, DataSourceModule::class, ServiceModule::class])
interface AppComponent {
    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun serviceModule(serviceModule: ServiceModule): Builder

        fun build(): AppComponent
    }
}
