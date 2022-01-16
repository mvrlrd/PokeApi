package ru.mvrlrd.pokeapi.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.mvrlrd.pokeapi.MyApplication
import ru.mvrlrd.pokeapi.data.database.PokemonDao
import ru.mvrlrd.pokeapi.data.database.PokemonDatabase
import ru.mvrlrd.pokeapi.data.repository.PokemonRepositoryImpl
import ru.mvrlrd.pokeapi.data.retrofit.RetrofitClient
import ru.mvrlrd.pokeapi.domain.repository.PokemonRepository
import ru.mvrlrd.pokeapi.ui.favorites.FavoritesViewModel
import ru.mvrlrd.pokeapi.ui.search.SearchViewModel
import javax.inject.Singleton

@Module
class RoomModule(application: MyApplication) {
     val pokemonDatabase = Room
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
    fun providePokemonRepository(pokemonDao: PokemonDao): PokemonRepository {
        return PokemonRepositoryImpl(pokemonDao)
    }

    @Singleton
    @Provides
    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel(RetrofitClient(), PokemonRepositoryImpl(pokemonDatabase.pokemonDao()))
    }

    @Singleton
    @Provides
    fun provideFavoritesViewModel(): FavoritesViewModel {
        return FavoritesViewModel(RetrofitClient(), PokemonRepositoryImpl(pokemonDatabase.pokemonDao()))
    }
}