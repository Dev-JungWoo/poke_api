package com.vincent.pokeapi.view.pokemons

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.vincent.entities.Pokemon
import com.vincent.pokeapi.R
import com.vincent.pokeapi.model.PokemonViewModel
import com.vincent.pokeapi.model.PokemonViewModelFactory
import com.vincent.pokeapi.services.PokeApiService
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokemons.*
import javax.inject.Inject


class PokemonListFragment : Fragment(), IPokemonsView {
    private val TAG = javaClass.simpleName

    private lateinit var pokemonsViewModel: PokemonViewModel

    @Inject
    lateinit var pokeApiService: PokeApiService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        pokemonsViewModel = ViewModelProviders.of(activity!!, PokemonViewModelFactory(pokeApiService)).get(PokemonViewModel::class.java)

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_pokemons, container, false)!!
    }

    override fun onStart() {
        super.onStart()

        pokemonListRecyclerView.layoutManager = LinearLayoutManager(activity)
        pokemonListRecyclerView.adapter = PokemonListAdapter(this)

        updateUI(pokemonsViewModel.pokemons.value)
        pokemonsViewModel.pokemons.observe(this, Observer {
            updateUI(it)
        })
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun updateUI(movieList: List<Pokemon>?) {
        Log.d(TAG, "updateUI, pokemonList = $movieList")

        val existingMovieList = (pokemonListRecyclerView.adapter as PokemonListAdapter).pokemonList

        movieList?.let {
            existingMovieList.addAll(it)
            pokemonListRecyclerView.adapter?.notifyDataSetChanged()
        }

        noPokemonTextView.visibility = if (existingMovieList.isEmpty()) {
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
                //TODO: filter list
                return false
            }
        })
    }
}
