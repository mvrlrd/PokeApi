package ru.mvrlrd.pokeapi.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mvrlrd.pokeapi.data.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon): Long

    @Query("SELECT * FROM favorite_pokemons_db")
    suspend fun getAllPokemons(): List<Pokemon>
}