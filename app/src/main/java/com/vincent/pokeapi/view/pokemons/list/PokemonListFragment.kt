package com.vincent.pokeapi.view.pokemons.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vincent.entities.Pokemon
import com.vincent.pokeapi.R
import com.vincent.pokeapi.services.PokeApiService
import com.vincent.pokeapi.view.pokemons.details.IPokemonListSelectListener
import com.vincent.pokeapi.view.pokemons.details.PokemonDetailsFragment
import com.vincent.pokeapi.view.pokemons.details.PokemonDetailsViewModel
import com.vincent.pokeapi.view.pokemons.details.PokemonDetailsViewModelFactory
import com.vincent.usecases.GetPokemonsState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokemons.*
import javax.inject.Inject

class PokemonListFragment : Fragment(), IPokemonListView, IPokemonListSelectListener {

    private lateinit var pokemonListViewModel: PokemonListViewModel
    private lateinit var pokemonList: LiveData<List<Pokemon>>

    @Inject
    lateinit var pokeApiService: PokeApiService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        pokemonListViewModel = ViewModelProvider(
            activity!!,
            PokemonListViewModelFactory(pokeApiService)
        ).get(
            PokemonListViewModel::class.java
        )

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_pokemons, container, false)!!
    }

    override fun onStart() {
        super.onStart()

//        pokemonListRecyclerView.layoutManager = LinearLayoutManager(activity)
//        pokemonListRecyclerView.adapter = PokemonListAdapter(this)
//
//        when (pokemonListViewModel.getPokemons().value) {
//            GetPokemonsState.Loading -> {}
//            is GetPokemonsState.Loaded -> updateUI((pokemonListViewModel.getPokemons().value as GetPokemonsState.Loaded).pokemons)
//            GetPokemonsState.Failed -> {}
//            null -> {}
//        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onSelect(pokemon: Pokemon) {
        activity?.supportFragmentManager?.beginTransaction()?.let { transaction ->
            val pokemonDetailsFragment = PokemonDetailsFragment()

            activity?.run {
                ViewModelProvider(
                    activity!!,
                    PokemonDetailsViewModelFactory(
                        pokeApiService
                    )
                ).get(PokemonDetailsViewModel::class.java).pokemon = pokemon
            } ?: throw Exception("Invalid Activity")

            transaction.replace(R.id.main_container, pokemonDetailsFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun updateUI(pokemonList: List<Pokemon>?) {
        Log.d(TAG, "updateUI, pokemonList = $pokemonList")

        val existingPokemonList = (pokemonListRecyclerView.adapter as PokemonListAdapter).pokemonList

        pokemonList?.let {
            existingPokemonList.addAll(it)
            pokemonListRecyclerView.adapter?.notifyDataSetChanged()
        }

        noPokemonTextView.visibility = if (existingPokemonList.isEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.searchView)
        val searchView = searchItem.actionView as SearchView
        searchView.isIconified = false
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d(TAG, "onQueryTextSubmit")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val existingPokemonList = (pokemonListRecyclerView.adapter as PokemonListAdapter).pokemonList
                existingPokemonList.clear()

                val listToAdd: List<Pokemon>? = if (newText.isEmpty()) {
                    pokemonList.value
                } else {
                    pokemonList.value?.filter {
                        it.name.contains(newText, true)
                    }
                }

                listToAdd?.let {
                    existingPokemonList.addAll(it)
                    (pokemonListRecyclerView.adapter as PokemonListAdapter).notifyDataSetChanged()
                }

                return false
            }
        })
    }

    companion object {
        val TAG: String = PokemonListFragment::class.java.simpleName
    }
}
