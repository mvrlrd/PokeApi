package ru.mvrlrd.pokeapi.data

import ru.mvrlrd.pokeapi.domain.models.Pokemon

fun mapPokemonModelToPokemonDomain(pokemonModel: PokemonModel) =
    Pokemon(
        pokemonModel.id,
        pokemonModel.name,
        pokemonModel.weight,
        pokemonModel.height,
        pokemonModel.url
    )

fun mapPokemonDomainToPokemonModel(pokemon: Pokemon) =
    PokemonModel(
        pokemon.id,
        pokemon.name,
        pokemon.weight,
        pokemon.height,
        pokemon.url
    )

fun convertListOfModels(listModels: List<PokemonModel>): List<Pokemon>{
    return listModels.map {
        mapPokemonModelToPokemonDomain(it)
    }
}