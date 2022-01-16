package ru.mvrlrd.pokeapi.domain.repository

import io.reactivex.Observable
import ru.mvrlrd.pokeapi.data.retrofit.PokemonApi

interface RemoteDataSource {
    fun getPokemonByNameOrId(param: String): Observable<PokemonApi>
}