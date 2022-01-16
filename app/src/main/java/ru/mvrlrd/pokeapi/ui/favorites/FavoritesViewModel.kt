package ru.mvrlrd.pokeapi.ui.favorites

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import ru.mvrlrd.pokeapi.data.retrofit.PokemonApi
import ru.mvrlrd.pokeapi.data.retrofit.RetrofitClient
import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import ru.mvrlrd.pokeapi.domain.usecase.GetAllPokemonsUseCase
import ru.mvrlrd.pokeapi.domain.usecase.SavePokemonUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesViewModel @Inject constructor(
    retrofitClient: RetrofitClient,
    pokemonRepository: PokemonRepository

) : ViewModel() {
    private var apiService = retrofitClient.getApiService()

    private val _pokemon = MutableLiveData<PokemonApi>()

    val pokemon: LiveData<PokemonApi> = _pokemon

    private var _favoritePokemons = MutableLiveData<List<Pokemon>>()
    val favoritePokemons = _favoritePokemons

    private val savePokemonUseCase : SavePokemonUseCase = SavePokemonUseCase(pokemonRepository)
    private val getAllPokemonsUseCase : GetAllPokemonsUseCase = GetAllPokemonsUseCase(pokemonRepository)

    @SuppressLint("CheckResult")
    fun getPokemonByNameOrId(query: String) {
        apiService.search(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _pokemon.value = value },
                { error -> println("Error: $error") },
                { println("Completed!") }
            )
    }

    fun savePokemon(pokemon: PokemonApi){
        viewModelScope.launch {
            pokemon.sprites.front_default?.let {
                Pokemon(pokemon.id,
                    pokemon.name,
                    pokemon.weight,
                    pokemon.height,
                    it
                )
            }?.let { savePokemonUseCase.execute(it) }
        }
    }

    fun getAllFavoritePokemons() {
        viewModelScope.launch {
            _favoritePokemons.value = getAllPokemonsUseCase.execute()
        }
    }
}