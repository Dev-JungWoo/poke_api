package com.vincent.pokeapi.di

import com.vincent.pokeapi.MainActivity
import com.vincent.pokeapi.view.pokemons.details.PokemonDetailsFragment
import com.vincent.pokeapi.view.pokemons.list.PokemonListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun bindPokemonFragment(): PokemonListFragment

    @ContributesAndroidInjector()
    abstract fun bindPokemonDetailsFragment(): PokemonDetailsFragment
}