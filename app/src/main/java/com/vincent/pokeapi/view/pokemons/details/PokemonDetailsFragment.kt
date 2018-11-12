package com.vincent.pokeapi.view.pokemons.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import com.vincent.pokeapi.R
import com.vincent.pokeapi.model.PokemonDetailsViewModel
import com.vincent.pokeapi.model.PokemonDetailsViewModelFactory
import com.vincent.pokeapi.services.PokeApiService
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.io.InputStream
import java.net.URL
import javax.inject.Inject


class PokemonDetailsFragment : Fragment(), IPokemonDetailsView {
    private val TAG = javaClass.simpleName

    private lateinit var pokemonDetailsViewModel: PokemonDetailsViewModel

    lateinit var pokemon: Pokemon

    @Inject
    lateinit var pokeApiService: PokeApiService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        pokemonDetailsViewModel = ViewModelProviders.of(activity!!, PokemonDetailsViewModelFactory(pokeApiService)).get(PokemonDetailsViewModel::class.java)
        pokemonDetailsViewModel.pokemon = pokemon

        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)!!
    }

    override fun onStart() {
        super.onStart()

        updateUI(pokemonDetailsViewModel.pokemon)

        val observer = pokemonDetailsViewModel.pokemonDetails.observe(this, Observer {
            updateUI(it)
        })

        pokemonImageView.visibility = View.INVISIBLE

        launch { pokemonDetailsViewModel.getPokemonDetails(pokemonDetailsViewModel.pokemon.name) }
    }

    private fun updateUI(pokemon: Pokemon?) {
        Log.d(TAG, "updateUI, pokemon = $pokemon")

        pokemon?.let {
            pokemonNameTextView.text = pokemon.name
        }
    }

    private fun updateUI(details: PokemonDetails?) {
        Log.d(TAG, "updateUI, details = $details")

        details?.let {
            weightTextView.text = details.weight.toString()
            heightTextView.text = details.height.toString()
            launch(UI) {
                val imageLoadJob = async { loadImage(details.imageUrl) }
                imageLoadJob.await()?.let {
                    pokemonImageView.setImageBitmap(it)
                    pokemonImageView.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun loadImage(url: String): Bitmap? {
        val inputStream: InputStream?
        var bitmap: Bitmap? = null
        try {
            inputStream = URL(url).openStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            Log.e("Error", e.message, e)
        }

        return bitmap
    }
}
