package ru.mvrlrd.pokeapi.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_favorite.view.*
import ru.mvrlrd.pokeapi.R
import ru.mvrlrd.pokeapi.domain.models.Pokemon
import kotlin.properties.Delegates

private const val TAG = "FavoritesAdapter"

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.PokemonCardHolder>() {

    internal var collection: MutableList<Pokemon> by
    Delegates.observable(mutableListOf()) { _, _, _ -> notifyDataSetChanged() }

    init {
        setHasStableIds(true)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonCardHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return PokemonCardHolder(
             view
        )
    }

    override fun getItemCount() = collection.size

    override fun getItemId(position: Int): Long = collection[position].id.toLong()



    override fun onBindViewHolder(holder: PokemonCardHolder, position: Int) {
        val pokemonCard = collection[position]
            holder.bind(pokemonCard)
    }

    class PokemonCardHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(pokemon: Pokemon) {
         itemView.name_favorites.text = pokemon.name
            itemView.weight_favorites.text = pokemon.getWeight()
            itemView.height_favorites.text = pokemon.getHeight()
            itemView.pokemon_image_favorites.load(pokemon.url)
        }
    }
}
