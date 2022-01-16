package ru.mvrlrd.pokeapi.domain.repository

import ru.mvrlrd.pokeapi.domain.models.Pokemon

interface PokemonRepository {
    suspend fun savePokemon(pokemon: Pokemon):Long
    suspend fun getAllPokemons(): List<Pokemon>
}