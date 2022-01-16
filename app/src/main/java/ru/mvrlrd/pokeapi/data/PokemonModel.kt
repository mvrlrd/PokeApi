package ru.mvrlrd.pokeapi.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @Expose @field:SerializedName("id") val id : Int,
    @Expose @field:SerializedName("name") val name : String,
    @Expose @field:SerializedName("weight") val weight : Int,
    @Expose @field:SerializedName("height") val height : Int,
    @Expose @field:SerializedName("sprites") val sprites : Sprites
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
