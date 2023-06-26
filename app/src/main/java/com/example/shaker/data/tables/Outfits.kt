package com.example.shaker.data.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "outfits", foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["login"],
        childColumns = ["userLogin"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Outfit(
    @PrimaryKey(autoGenerate = true)
    val outfitId: Int,
    val outfitName: String,
    val userLogin: String
)