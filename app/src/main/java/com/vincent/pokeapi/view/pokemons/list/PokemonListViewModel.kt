package com.vincent.pokeapi.view.pokemons.list

import androidx.lifecycle.*
import com.vincent.pokeapi.DefaultDispatcherProvider
import com.vincent.pokeapi.DispatcherProvider
import com.vincent.usecases.GetPokemons
import com.vincent.usecases.GetPokemonsState
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonListViewModel(private val pokeService: IPokeService,
                           private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()) : ViewModel() {

//    private val _getPokemonsState = MutableLiveData<GetPokemonsState>()
//    val getPokemonsState: LiveData<GetPokemonsState> = _getPokemonsState
//
//    fun loadPokemons() {
//        viewModelScope.launch(dispatcherProvider.io()) {
//            _getPokemonsState.postValue(GetPokemonsState.Loading)
//            _getPokemonsState.postValue(GetPokemonsState.Loaded(GetPokemons(pokeService).execute()))
//        }
//    }

    fun getPokemons() = liveData {
        emit(GetPokemonsState.Loading)

        withContext(dispatcherProvider.io()) {
            GetPokemons(pokeService).execute()
        }?.let {
            emit(GetPokemonsState.Loaded(it))
        }
    }
}
