package ru.mvrlrd.pokeapi.ui.random

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import ru.mvrlrd.pokeapi.data.retrofit.ApiService
import ru.mvrlrd.pokeapi.data.retrofit.PokemonApi
import ru.mvrlrd.pokeapi.domain.models.Pokemon
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import ru.mvrlrd.pokeapi.domain.usecase.SavePokemonUseCase
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

const val START_NUMBER = 1
const val LAST_NUMBER = 898

@Singleton
class RandomViewModel @Inject constructor(
    private val apiService: ApiService,
    pokemonRepository: PokemonRepository
) : ViewModel() {
    private val _randomPokemon = MutableLiveData<PokemonApi>()
    val randomPokemon: LiveData<PokemonApi> = _randomPokemon
    private val savePokemonUseCase : SavePokemonUseCase = SavePokemonUseCase(pokemonRepository)

    private fun getRandomPokemonsId() = Random.nextInt(START_NUMBER, LAST_NUMBER)

    @SuppressLint("CheckResult")
    fun getRandomPokemon() {
        apiService.search(getRandomPokemonsId().toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _randomPokemon.value = value },
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
}