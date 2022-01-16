package ru.mvrlrd.pokeapi.ui.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.usecase.GetPokemonRemotelyUseCase
import ru.mvrlrd.pokeapi.domain.usecase.SavePokemonUseCase
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchViewModel @Inject constructor(
    private val savePokemonUseCase: SavePokemonUseCase,
    private val getPokemonRemotelyUseCase: GetPokemonRemotelyUseCase
) : ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    @SuppressLint("CheckResult")
    fun getPokemonByNameOrId(query: String) {
        Log.e("SearchViewModel","$savePokemonUseCase\n$getPokemonRemotelyUseCase")
        getPokemonRemotelyUseCase.execute(query).map { pokemonApi ->
            Pokemon(
                pokemonApi.id,
                pokemonApi.name,
                pokemonApi.weight,
                pokemonApi.height,
                pokemonApi.sprites.front_default!!
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _pokemon.value = value },
                { error -> println("Error: $error") },
                { println("Completed!") }
            )
    }

    fun savePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
            savePokemonUseCase.execute(pokemon)
        }
    }
}
