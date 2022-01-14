package ru.mvrlrd.pokeapi.ui.dashboard

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mvrlrd.pokeapi.DaggerDaggerComponent
import kotlin.random.Random

class RandomViewModel : ViewModel() {
    private val apiService = DaggerDaggerComponent.create().retrofitClient().getApiService()

    private val _pokemonName = MutableLiveData<String>().apply {
        value = ""
    }
    val pokemonName: LiveData<String> = _pokemonName

    @SuppressLint("CheckResult")
    fun getRandomPokemon() {
        apiService.search(Random.nextInt(1,898).toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _pokemonName.value = value.name }, // onNext
                { error -> println("Error: $error") },    // onError
                { println("Completed!") }
            )
    }
}