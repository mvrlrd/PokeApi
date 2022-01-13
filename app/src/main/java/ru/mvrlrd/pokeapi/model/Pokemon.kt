package ru.mvrlrd.pokeapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @Expose @field:SerializedName("id") val id : Int,
    @Expose @field:SerializedName("name") val name : String
    )
