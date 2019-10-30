package com.vincent.pokeapi.view.pokemons.details

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vincent.entities.PokemonDetails
import com.vincent.pokeapi.R
import com.vincent.pokeapi.model.PokemonDetailsViewModel
import com.vincent.pokeapi.model.PokemonDetailsViewModelFactory
import com.vincent.pokeapi.services.PokeApiService
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
import javax.inject.Inject


class PokemonDetailsFragment : Fragment(), IPokemonDetailsView {

    private lateinit var pokemonDetailsViewModel: PokemonDetailsViewModel

    @Inject
    lateinit var pokeApiService: PokeApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pokemonDetailsViewModel =
            ViewModelProvider(activity!!, PokemonDetailsViewModelFactory(pokeApiService))
                .get(PokemonDetailsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)!!
    }

    override fun onStart() {
        super.onStart()

        pokemonDetailsViewModel.getPokemonDetails(pokemonDetailsViewModel.pokemon.name).observe(
            this,
            Observer { updateUI(it) }
        )
    }

    private fun updateUI(details: PokemonDetails?) {
        Log.d(TAG, "updateUI, details = $details")

        details?.let {
            weightTextView.text = details.weight.toString()
            heightTextView.text = details.height.toString()

            lifecycleScope.launch {
                val imageLoadJob = GlobalScope.async { loadImage(details.imageUrl) }
                imageLoadJob.await()?.let {
                    pokemonImageView.setImageBitmap(it)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
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

    companion object {
        val TAG: String = PokemonDetailsFragment::class.java.simpleName
    }
}
