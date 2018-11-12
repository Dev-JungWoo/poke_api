package com.vincent.pokeapi.di

import com.vincent.pokeapi.data.PokeDataSource
import com.vincent.pokeapi.data.RemotePokeDataSource
import com.vincent.pokeapi.services.RetrofitPokeService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataSourceModule {
    @Provides
    fun providesRemoteMovieDataSource(retrofit: Retrofit): PokeDataSource {
        return RemotePokeDataSource(retrofit.create(RetrofitPokeService::class.java))
    }
}