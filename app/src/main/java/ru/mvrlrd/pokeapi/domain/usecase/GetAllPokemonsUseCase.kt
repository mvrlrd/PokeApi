package ru.mvrlrd.pokeapi.domain.usecase

import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository

class GetAllPokemonsUseCase(private val pokemonRepository: PokemonRepository) {

    fun execute():List<Pokemon>{
        return pokemonRepository.getAllPokemons()
    }
}