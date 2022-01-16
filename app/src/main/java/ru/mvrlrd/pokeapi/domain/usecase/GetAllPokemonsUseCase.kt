package ru.mvrlrd.pokeapi.domain.usecase

import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import javax.inject.Singleton

@Singleton
class GetAllPokemonsUseCase(private val pokemonRepository: PokemonRepository) {

   suspend fun execute(): List<Pokemon> {
        return pokemonRepository.getAllPokemons()
    }
}