package ru.mvrlrd.pokeapi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_pokemons_db")
data class Pokemon(
    @PrimaryKey @Expose @field:SerializedName("id") val id : Int,
    @ColumnInfo @Expose @field:SerializedName("name") val name : String,
    @ColumnInfo @Expose @field:SerializedName("weight") val weight : Int,
    @ColumnInfo @Expose @field:SerializedName("height") val height : Int,
    @ColumnInfo @Expose @field:SerializedName("url") val url : String
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pokemon

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
