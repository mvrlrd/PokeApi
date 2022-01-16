package ru.mvrlrd.pokeapi.data.repository

import io.reactivex.Observable
import ru.mvrlrd.pokeapi.data.retrofit.ApiService
import ru.mvrlrd.pokeapi.data.retrofit.PokemonApi
import ru.mvrlrd.pokeapi.domain.repository.RemoteDataSource
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource{
    override fun getPokemonByNameOrId(param: String): Observable<PokemonApi> {
       return apiService.search(param)
    }
}