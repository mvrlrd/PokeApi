package ru.mvrlrd.pokeapi.domain.models

data class Pokemon(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val url: String
){
    fun getWeight() =
        "вес: $weight"

    fun getHeight() =
        "рост: $height"
}



