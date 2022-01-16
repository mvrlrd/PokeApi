package ru.mvrlrd.pokeapi.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mvrlrd.pokeapi.data.PokemonModel
import javax.inject.Singleton

@Dao
@Singleton
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonModel: PokemonModel): Long

    @Query("SELECT * FROM favorite_pokemons_db")
    suspend fun getAllPokemons(): List<PokemonModel>
}