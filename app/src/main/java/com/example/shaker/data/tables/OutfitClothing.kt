package com.example.shaker.data.tables

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "outfits_clothing", primaryKeys = ["clothingId", "outfitId"], foreignKeys = [
    ForeignKey(
        entity = Clothing::class,
        parentColumns = ["clothingId"],
        childColumns = ["clothingId"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Outfit::class,
        parentColumns = ["outfitId"],
        childColumns = ["outfitId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class OutfitClothing(
    val clothingId: String,
    val outfitId: Int
)