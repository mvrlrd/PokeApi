package ru.mvrlrd.pokeapi.domain.repository

import ru.mvrlrd.pokeapi.domain.models.Pokemon

interface PokemonRepository {
    fun savePokemon(pokemon: Pokemon):Int
    fun getAllPokemons(): List<Pokemon>
}