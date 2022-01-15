package ru.mvrlrd.pokeapi

import android.app.Application

class MyApplication : Application() {
    lateinit var appComponent: DaggerComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerDaggerComponent.create()
    }
}