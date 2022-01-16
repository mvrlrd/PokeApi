package ru.mvrlrd.pokeapi.data.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.mvrlrd.pokeapi.data.PokemonModel

interface ApiService {
        @GET("pokemon/{id}/")
        fun search(@Path("id") wordToSearch: String): Observable<PokemonModel>
    }