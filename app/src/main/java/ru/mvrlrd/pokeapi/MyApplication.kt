package ru.mvrlrd.pokeapi

import android.app.Application
import ru.mvrlrd.pokeapi.di.AppModule
import ru.mvrlrd.pokeapi.di.RoomModule

class MyApplication : Application() {
    lateinit var appComponent: DaggerComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerDaggerComponent.builder()
            .appModule(AppModule())
            .roomModule(RoomModule(this))
            .build()
    }
}