package com.vincent.pokeapi

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.vincent.pokeapi.view.pokemons.list.PokemonListFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerAppCompatActivity() {
    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val pokemonListFragment = PokemonListFragment()

            transaction.add(R.id.main_container, pokemonListFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        }

        setSupportActionBar(toolbar)
    }
}
