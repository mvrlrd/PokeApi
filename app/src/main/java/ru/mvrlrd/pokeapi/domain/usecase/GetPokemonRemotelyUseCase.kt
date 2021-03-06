package ru.mvrlrd.pokeapi.domain.usecase

import ru.mvrlrd.pokeapi.data.retrofit.PokemonApi
import ru.mvrlrd.pokeapi.domain.repository.RemoteDataSource
import javax.inject.Singleton

@Singleton
class GetPokemonRemotelyUseCase(private val remoteDataSource: RemoteDataSource) {

    fun execute(query: String): io.reactivex.Observable<PokemonApi>{
        return remoteDataSource.getPokemonByNameOrId(query)
    }
}