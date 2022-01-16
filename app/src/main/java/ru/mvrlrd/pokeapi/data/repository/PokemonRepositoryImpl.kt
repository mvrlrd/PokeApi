package ru.mvrlrd.pokeapi.data.repository

import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository

class PokemonRepositoryImpl: PokemonRepository {
    override fun savePokemon(pokemon: Pokemon): Int {
        TODO("Not yet implemented")
    }

    override fun getAllPokemons(): List<Pokemon> {
        TODO("Not yet implemented")
    }
}