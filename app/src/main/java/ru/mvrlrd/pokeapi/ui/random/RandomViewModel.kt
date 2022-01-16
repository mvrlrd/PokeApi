package ru.mvrlrd.pokeapi.ui.random

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
import kotlin.random.Random

const val START_NUMBER = 1
const val LAST_NUMBER = 898

const val TAG = "RandomViewModel"

@Singleton
class RandomViewModel @Inject constructor(
    private val savePokemonUseCase: SavePokemonUseCase,
    private val getPokemonRemotelyUseCase: GetPokemonRemotelyUseCase
) : ViewModel() {
    private val _randomPokemon = MutableLiveData<Pokemon>()
    val randomPokemon: LiveData<Pokemon> = _randomPokemon

    private fun getRandomPokemonsId() = Random.nextInt(START_NUMBER, LAST_NUMBER)

    @SuppressLint("CheckResult")
    fun getRandomPokemon() {
        Log.e(TAG,"$savePokemonUseCase\n$getPokemonRemotelyUseCase")
        getPokemonRemotelyUseCase.execute(getRandomPokemonsId().toString()).map {
            Pokemon(
                it.id,
                it.name,
                it.weight,
                it.height,
                it.sprites.front_default!!
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _randomPokemon.value = value },
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