package ru.mvrlrd.pokeapi.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mvrlrd.pokeapi.data.retrofit.PokemonApi
import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import ru.mvrlrd.pokeapi.domain.usecase.GetAllPokemonsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {
    private var _favoritePokemons = MutableLiveData<List<Pokemon>>()
    val favoritePokemons = _favoritePokemons
    private val getAllPokemonsUseCase : GetAllPokemonsUseCase = GetAllPokemonsUseCase(pokemonRepository)

    fun getAllFavoritePokemons() {
        viewModelScope.launch {
            _favoritePokemons.value = getAllPokemonsUseCase.execute()
        }
    }
}