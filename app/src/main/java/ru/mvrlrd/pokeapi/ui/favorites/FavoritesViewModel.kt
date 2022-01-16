package ru.mvrlrd.pokeapi.ui.favorites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.usecase.GetAllPokemonsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesViewModel @Inject constructor(
    private val getAllPokemonsUseCase : GetAllPokemonsUseCase
) : ViewModel() {

    private var _favoritePokemons = MutableLiveData<List<Pokemon>>()
    val favoritePokemons = _favoritePokemons

    fun getAllFavoritePokemons() {
        Log.e("FavoritesViewModel","$getAllPokemonsUseCase")
        viewModelScope.launch {
            _favoritePokemons.value = getAllPokemonsUseCase.execute()
        }
    }
}