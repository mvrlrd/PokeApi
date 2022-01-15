package ru.mvrlrd.pokeapi.di

import dagger.Module
import dagger.Provides
import ru.mvrlrd.pokeapi.model.retrofit.RetrofitClient
import ru.mvrlrd.pokeapi.ui.random.RandomViewModel
import ru.mvrlrd.pokeapi.ui.search.SearchViewModel
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): RetrofitClient{
        return RetrofitClient()
    }
    @Singleton
    @Provides
    fun provideSearchViewModel(): SearchViewModel{
        return SearchViewModel(RetrofitClient())
    }

    @Singleton
    @Provides
    fun provideRandomViewModel(): RandomViewModel{
        return RandomViewModel(RetrofitClient())
    }


}