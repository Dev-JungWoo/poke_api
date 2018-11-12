package com.vincent.pokeapi.data

import com.vincent.pokeapi.services.RetrofitPokeService
import com.vincent.pokeapi.services.ServiceModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [ServiceModule::class])
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(retrofit: Retrofit): PokeDataSource {
        return RemotePokeDataSource(retrofit.create(RetrofitPokeService::class.java))
    }
}
