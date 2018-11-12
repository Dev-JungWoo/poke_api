package com.vincent.pokeapi.view.pokemons

import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.vincent.entities.Pokemon
import com.vincent.pokeapi.R
import com.vincent.pokeapi.view.pokemons.details.PokemonDetailsFragment

class PokemonListAdapter(var pokemonListFragment: PokemonListFragment) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    val TAG = javaClass.simpleName

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
            val transaction = pokemonListFragment.activity?.supportFragmentManager?.beginTransaction()

            transaction?.let {transaction ->
                val pokemonListFragment = PokemonDetailsFragment()
                pokemonListFragment.pokemon = pokemon
                transaction.replace(R.id.main_container, pokemonListFragment)
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}
