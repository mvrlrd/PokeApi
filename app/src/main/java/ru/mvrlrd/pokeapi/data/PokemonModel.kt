package ru.mvrlrd.pokeapi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pokemons_db")
data class PokemonModel(
    @PrimaryKey  val id : Int,
    @ColumnInfo  val name : String,
    @ColumnInfo  val weight : Int,
    @ColumnInfo  val height : Int,
    @ColumnInfo  val url : String
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
