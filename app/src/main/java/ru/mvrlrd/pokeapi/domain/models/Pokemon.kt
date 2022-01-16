package ru.mvrlrd.pokeapi.domain.models

import ru.mvrlrd.pokeapi.data.Sprites

data class Pokemon(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites
)

