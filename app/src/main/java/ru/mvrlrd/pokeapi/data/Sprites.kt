package ru.mvrlrd.pokeapi.data

import com.google.gson.annotations.Expose

data class Sprites(
    @Expose val back_default:String?,
    @Expose  val back_female:String?,
    @Expose  val back_shiny:String?,
    @Expose  val back_shiny_female:String?,
    @Expose  val front_default:String?,
    @Expose  val front_female:String?,
    @Expose  val front_shiny:String?,
    @Expose  val front_shiny_female:String?,
)
