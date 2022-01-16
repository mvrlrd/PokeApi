package ru.mvrlrd.pokeapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mvrlrd.pokeapi.data.PokemonModel
import javax.inject.Singleton

@Singleton
@Database(entities = [PokemonModel::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}