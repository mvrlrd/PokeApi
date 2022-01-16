package ru.mvrlrd.pokeapi

import dagger.Component
import ru.mvrlrd.pokeapi.data.database.PokemonDao
import ru.mvrlrd.pokeapi.data.database.PokemonDatabase
import ru.mvrlrd.pokeapi.data.retrofit.RetrofitClient
import ru.mvrlrd.pokeapi.di.AppModule
import ru.mvrlrd.pokeapi.di.RoomModule
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import ru.mvrlrd.pokeapi.ui.favorites.FavoritesViewModel
import ru.mvrlrd.pokeapi.ui.random.RandomViewModel
import ru.mvrlrd.pokeapi.ui.search.SearchViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface DaggerComponent {
    fun retrofitClient(): RetrofitClient
    fun injectSearchVM(): SearchViewModel
    fun injectRandomVM(): RandomViewModel
    fun injectFavoritesVM(): FavoritesViewModel

    fun pokemonDao(): PokemonDao
    fun pokemonDatabase(): PokemonDatabase
    fun pokemonRepository(): PokemonRepository
}