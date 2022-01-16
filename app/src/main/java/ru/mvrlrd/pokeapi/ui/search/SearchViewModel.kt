package ru.mvrlrd.pokeapi.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mvrlrd.pokeapi.data.Pokemon
import ru.mvrlrd.pokeapi.data.retrofit.RetrofitClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class  SearchViewModel @Inject constructor(retrofitClient: RetrofitClient) : ViewModel() {
    private  var apiService = retrofitClient.getApiService()

    private val _pokemon = MutableLiveData<Pokemon>()

    val pokemon: LiveData<Pokemon> = _pokemon

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

}