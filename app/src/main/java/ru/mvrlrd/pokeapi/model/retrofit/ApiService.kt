package ru.mvrlrd.pokeapi.model.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.mvrlrd.pokeapi.model.Pokemon

interface ApiService {
        @GET("pokemon/{id}/")
        fun search(@Path("id") wordToSearch: String): Observable<Pokemon>
    }