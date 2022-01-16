package ru.mvrlrd.pokeapi.data.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.mvrlrd.pokeapi.data.Sprites


data class PokemonApi(
    @Expose @field:SerializedName("id") val id : Int,
    @Expose @field:SerializedName("name") val name : String,
    @Expose @field:SerializedName("weight") val weight : Int,
    @Expose @field:SerializedName("height") val height : Int,
    @Expose @field:SerializedName("sprites") val sprites : Sprites
)

