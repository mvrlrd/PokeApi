package ru.mvrlrd.pokeapi.data.repository

import kotlinx.coroutines.flow.Flow
import ru.mvrlrd.pokeapi.data.Pokemon
import ru.mvrlrd.pokeapi.data.database.PokemonDao
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import javax.inject.Inject


class PokemonRepositoryImpl@Inject constructor(private val pokemonDao: PokemonDao): PokemonRepository {
    override suspend fun savePokemon(pokemon: Pokemon): Long {
        return pokemonDao.insert(pokemon)
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return pokemonDao.getAllPokemons()
    }
}