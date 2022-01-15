package ru.mvrlrd.pokeapi

import dagger.Component
import ru.mvrlrd.pokeapi.model.retrofit.RetrofitClient
import ru.mvrlrd.pokeapi.ui.favorites.FavoritesViewModel
import ru.mvrlrd.pokeapi.ui.random.RandomViewModel
import ru.mvrlrd.pokeapi.ui.search.SearchViewModel
import javax.inject.Singleton

@Singleton
@Component
interface DaggerComponent {
    fun retrofitClient(): RetrofitClient
    fun injectSearchVM(): SearchViewModel
    fun injectRandomVM(): RandomViewModel
    fun injectFavoritesVM(): FavoritesViewModel
}