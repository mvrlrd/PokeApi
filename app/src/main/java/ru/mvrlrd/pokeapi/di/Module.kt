package ru.mvrlrd.pokeapi.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.mvrlrd.pokeapi.MyApplication
import ru.mvrlrd.pokeapi.data.database.PokemonDao
import ru.mvrlrd.pokeapi.data.database.PokemonDatabase
import ru.mvrlrd.pokeapi.data.repository.PokemonRepositoryImpl
import ru.mvrlrd.pokeapi.data.repository.RemoteDataSourceImpl
import ru.mvrlrd.pokeapi.data.retrofit.ApiService
import ru.mvrlrd.pokeapi.data.retrofit.RetrofitClient
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import ru.mvrlrd.pokeapi.domain.repository.RemoteDataSource
import ru.mvrlrd.pokeapi.domain.usecase.GetAllPokemonsUseCase
import ru.mvrlrd.pokeapi.domain.usecase.GetPokemonRemotelyUseCase
import ru.mvrlrd.pokeapi.domain.usecase.SavePokemonUseCase
import ru.mvrlrd.pokeapi.ui.favorites.FavoritesViewModel
import ru.mvrlrd.pokeapi.ui.random.RandomViewModel
import ru.mvrlrd.pokeapi.ui.search.SearchViewModel
import javax.inject.Singleton

@Module
class Module(application: MyApplication) {
     private val pokemonDatabase = Room
        .databaseBuilder(
            application,
            PokemonDatabase::class.java,
            "favorite_pokemons_db"
        ).build()

    @Singleton
    @Provides
    fun providePokemonDatabase(): PokemonDatabase {
        return pokemonDatabase
    }
    @Singleton
    @Provides
    fun providePokemonDao(): PokemonDao {
        return pokemonDatabase.pokemonDao()
    }
    @Singleton
    @Provides
    fun providePokemonRepository(): PokemonRepository {
        return PokemonRepositoryImpl(providePokemonDao())
    }



    @Singleton
    @Provides
    fun provideRetrofitClient(): RetrofitClient{
        return RetrofitClient()
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitClient().getApiService()
    }
    @Singleton
    @Provides
    fun provideRemoteRepository(): RemoteDataSource {
        return RemoteDataSourceImpl(provideApiService())
    }



    @Singleton
    @Provides
    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel(provideSavePokemonUseCase(), provideGetPokemonRemotelyUseCase())
    }
    @Singleton
    @Provides
    fun provideRandomViewModel(): RandomViewModel {
        return RandomViewModel(provideSavePokemonUseCase(), provideGetPokemonRemotelyUseCase())
    }
    @Singleton
    @Provides
    fun provideFavoritesViewModel(): FavoritesViewModel {
        return FavoritesViewModel(provideGetAllPokemonsUseCase())
    }

    @Singleton
    @Provides
    fun provideGetAllPokemonsUseCase(): GetAllPokemonsUseCase {
        return GetAllPokemonsUseCase(providePokemonRepository())
    }
    @Singleton
    @Provides
    fun provideSavePokemonUseCase(): SavePokemonUseCase {
        return SavePokemonUseCase(providePokemonRepository())
    }
    @Singleton
    @Provides
    fun provideGetPokemonRemotelyUseCase(): GetPokemonRemotelyUseCase {
        return GetPokemonRemotelyUseCase(provideRemoteRepository())
    }
}

