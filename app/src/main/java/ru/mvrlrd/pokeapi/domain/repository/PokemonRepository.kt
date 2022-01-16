package ru.mvrlrd.pokeapi.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mvrlrd.pokeapi.data.Pokemon
import javax.inject.Inject

interface PokemonRepository {
    suspend fun savePokemon(pokemon: Pokemon):Long
    suspend fun getAllPokemons(): List<Pokemon>
}