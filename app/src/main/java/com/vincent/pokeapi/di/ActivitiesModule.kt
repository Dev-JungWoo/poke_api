package com.vincent.pokeapi.di

import com.vincent.pokeapi.MainActivity
import com.vincent.pokeapi.MainActivityModule
import com.vincent.pokeapi.view.pokemons.PokemonListFragment
import com.vincent.pokeapi.view.pokemons.PokemonsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [PokemonsFragmentModule::class])
    abstract fun bindMoviesFragment(): PokemonListFragment
}