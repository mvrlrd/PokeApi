package ru.mvrlrd.pokeapi.data.repository

import ru.mvrlrd.pokeapi.data.PokemonModel
import ru.mvrlrd.pokeapi.data.convertListOfModels
import ru.mvrlrd.pokeapi.data.database.PokemonDao
import ru.mvrlrd.pokeapi.data.mapPokemonDomainToPokemonModel
import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import javax.inject.Inject


class PokemonRepositoryImpl@Inject constructor(private val pokemonDao: PokemonDao): PokemonRepository {
    override suspend fun savePokemon(pokemon: Pokemon): Long {
        return pokemonDao.insert(mapPokemonDomainToPokemonModel(pokemon))
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return convertListOfModels(pokemonDao.getAllPokemons())
    }
}