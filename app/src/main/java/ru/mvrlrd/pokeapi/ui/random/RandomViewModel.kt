package ru.mvrlrd.pokeapi.ui.random

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mvrlrd.pokeapi.data.retrofit.PokemonApi
import ru.mvrlrd.pokeapi.data.retrofit.RetrofitClient
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

const val START_NUMBER = 1
const val LAST_NUMBER = 898

@Singleton
class RandomViewModel @Inject constructor(retrofitClient: RetrofitClient) : ViewModel() {
    private val apiService = retrofitClient.getApiService()
    private val _randomPokemon = MutableLiveData<PokemonApi>()
    val randomPokemon: LiveData<PokemonApi> = _randomPokemon




    @SuppressLint("CheckResult")
    fun getRandomPokemon() {
        apiService.search(Random.nextInt(START_NUMBER, LAST_NUMBER).toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _randomPokemon.value = value },
                { error -> println("Error: $error") },
                { println("Completed!") }
            )
    }



}