package ru.mvrlrd.pokeapi

import android.app.Application
import ru.mvrlrd.pokeapi.di.Module

class MyApplication : Application() {
    lateinit var appComponent: DaggerComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerDaggerComponent.builder()
            .module(Module(this))
            .build()
    }
}