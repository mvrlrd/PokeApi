package ru.mvrlrd.pokeapi.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import ru.mvrlrd.pokeapi.data.retrofit.PokemonApi
import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import ru.mvrlrd.pokeapi.domain.repository.RemoteDataSource
import ru.mvrlrd.pokeapi.domain.usecase.GetPokemonRemotely
import ru.mvrlrd.pokeapi.domain.usecase.SavePokemonUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchViewModel @Inject constructor(
    remoteDataSource: RemoteDataSource,
    pokemonRepository: PokemonRepository
) : ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    private val savePokemonUseCase: SavePokemonUseCase = SavePokemonUseCase(pokemonRepository)
    private val getPokemonRemotely: GetPokemonRemotely = GetPokemonRemotely(remoteDataSource)

    @SuppressLint("CheckResult")
    fun getPokemonByNameOrId(query: String) {
        getPokemonRemotely.execute(query).map { pokemonApi ->
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
