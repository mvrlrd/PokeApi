package ru.mvrlrd.pokeapi

import dagger.Component
import ru.mvrlrd.pokeapi.di.Module
import ru.mvrlrd.pokeapi.ui.favorites.FavoritesViewModel
import ru.mvrlrd.pokeapi.ui.random.RandomViewModel
import ru.mvrlrd.pokeapi.ui.search.SearchViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class])
interface DaggerComponent {

    fun injectSearchVM(): SearchViewModel
    fun injectRandomVM(): RandomViewModel
    fun injectFavoritesVM(): FavoritesViewModel
}