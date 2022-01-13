package ru.mvrlrd.pokeapi

import dagger.Component
import ru.mvrlrd.pokeapi.model.retrofit.RetrofitClient

@Component
interface DaggerComponent {
    fun retrofitClient(): RetrofitClient
}