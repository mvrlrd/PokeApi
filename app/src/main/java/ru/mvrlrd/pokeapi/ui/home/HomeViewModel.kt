package ru.mvrlrd.pokeapi.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mvrlrd.pokeapi.DaggerDaggerComponent

class HomeViewModel : ViewModel() {
    private val apiService = DaggerDaggerComponent.create().retrofitClient().getApiService()

    private val _pokemonName = MutableLiveData<String>().apply {
        value = ""
    }
    val pokemonName: LiveData<String> = _pokemonName

    @SuppressLint("CheckResult")
     fun getPokemon(id: String) {
        apiService.search(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _pokemonName.value = value.name }, // onNext
                { error -> println("Error: $error") },    // onError
                { println("Completed!") }
            )
    }
}