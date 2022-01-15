package ru.mvrlrd.pokeapi.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mvrlrd.pokeapi.DaggerDaggerComponent
import ru.mvrlrd.pokeapi.model.Pokemon

class SearchViewModel : ViewModel() {
    private val apiService = DaggerDaggerComponent.create().retrofitClient().getApiService()

    private val _pokemon = MutableLiveData<Pokemon>()

    val pokemonName: LiveData<Pokemon> = _pokemon

    @SuppressLint("CheckResult")
     fun getPokemon(id: String) {
        apiService.search(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> _pokemon.value = value }, // onNext
                { error -> println("Error: $error") },    // onError
                { println("Completed!") }
            )
    }
}