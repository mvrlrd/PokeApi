package ru.mvrlrd.pokeapi.ui.random

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mvrlrd.pokeapi.model.Pokemon
import ru.mvrlrd.pokeapi.model.retrofit.RetrofitClient
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

const val START_NUMBER = 1
const val LAST_NUMBER = 898

@Singleton
class RandomViewModel @Inject constructor(retrofitClient: RetrofitClient) : ViewModel() {
    private val apiService = retrofitClient.getApiService()
    private val _randomPokemon = MutableLiveData<Pokemon>()
    val randomPokemon: LiveData<Pokemon> = _randomPokemon

    @SuppressLint("CheckResult")
    fun getRandomPokemon() {
        apiService.search(Random.nextInt(START_NUMBER, LAST_NUMBER).toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _randomPokemon.value = value }, // onNext
                { error -> println("Error: $error") },    // onError
                { println("Completed!") }
            )
    }

}