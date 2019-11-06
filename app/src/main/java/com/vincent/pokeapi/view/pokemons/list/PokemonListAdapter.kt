package com.vincent.pokeapi.view.pokemons.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vincent.entities.Pokemon
import com.vincent.pokeapi.R
import com.vincent.pokeapi.view.pokemons.details.IPokemonListSelectListener

class PokemonListAdapter(private val selectListener: IPokemonListSelectListener) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    private val TAG = javaClass.simpleName

    var pokemonList: MutableList<Pokemon> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")

        val pokemon = pokemonList[position]
        val cardView = holder.cardView
        val nameTextView = cardView.findViewById(R.id.pokemonNameTextView) as TextView

        nameTextView.text = pokemon.name

        cardView.setOnClickListener {
            selectListener.onSelect(pokemon)
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}
