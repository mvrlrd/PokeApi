package ru.mvrlrd.pokeapi.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.mvrlrd.pokeapi.data.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository

class GetAllPokemonsUseCase(private val pokemonRepository: PokemonRepository) {

   suspend fun execute(): List<Pokemon> {
        return pokemonRepository.getAllPokemons()
    }
}