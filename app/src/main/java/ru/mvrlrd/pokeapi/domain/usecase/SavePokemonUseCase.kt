package ru.mvrlrd.pokeapi.domain.usecase

import ru.mvrlrd.pokeapi.data.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository

class SavePokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun execute(pokemon: Pokemon):Long{
        return pokemonRepository.savePokemon(pokemon)
    }
}