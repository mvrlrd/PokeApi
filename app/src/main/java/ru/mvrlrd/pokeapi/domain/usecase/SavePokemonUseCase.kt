package ru.mvrlrd.pokeapi.domain.usecase

import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository

class SavePokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun execute(pokemonModel: Pokemon):Long{
        return pokemonRepository.savePokemon(pokemonModel)
    }
}