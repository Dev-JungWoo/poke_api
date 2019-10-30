package com.vincent.pokeapi.view.pokemons

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vincent.entities.Pokemon
import com.vincent.pokeapi.R
import com.vincent.pokeapi.model.PokemonDetailsViewModel
import com.vincent.pokeapi.model.PokemonDetailsViewModelFactory
import com.vincent.pokeapi.model.PokemonListViewModel
import com.vincent.pokeapi.model.PokemonListViewModelFactory
import com.vincent.pokeapi.services.PokeApiService
import com.vincent.pokeapi.view.pokemons.details.IPokemonListSelectListener
import com.vincent.pokeapi.view.pokemons.details.PokemonDetailsFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokemons.*
import javax.inject.Inject

class PokemonListFragment : Fragment(), IPokemonsView, IPokemonListSelectListener {
    private val TAG = javaClass.simpleName

    private lateinit var pokemonListViewModel: PokemonListViewModel

    @Inject
    lateinit var pokeApiService: PokeApiService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        pokemonListViewModel = ViewModelProviders.of(
            activity!!,
            PokemonListViewModelFactory(pokeApiService)).get(PokemonListViewModel::class.java
        )

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_pokemons, container, false)!!
    }

    override fun onStart() {
        super.onStart()

        pokemonListRecyclerView.layoutManager = LinearLayoutManager(activity)
        pokemonListRecyclerView.adapter = PokemonListAdapter(this)

        updateUI(pokemonListViewModel.pokemons.value)
        pokemonListViewModel.pokemons.observe(this, Observer {
            updateUI(it)
        })

        val pokemonList = pokemonListViewModel.pokemons.value
        if (pokemonList == null || pokemonList.isEmpty()) {
            pokemonListViewModel.getPokemons()
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onSelect(pokemon: Pokemon) {
        activity?.supportFragmentManager?.beginTransaction()?.let { transaction ->
            val pokemonDetailsFragment = PokemonDetailsFragment()

            activity?.run {
                ViewModelProviders.of(activity!!, PokemonDetailsViewModelFactory(pokeApiService)).get(PokemonDetailsViewModel::class.java).pokemon = pokemon
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
                    pokemonListViewModel.pokemons.value
                } else {
                    pokemonListViewModel.pokemons.value?.filter {
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
}
