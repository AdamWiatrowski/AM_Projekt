package com.example.shaker.data.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "clothing", foreignKeys = [
    ForeignKey(
        entity = Tag::class,
        parentColumns = ["tagName"],
        childColumns = ["tagName"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Clothing(
    @PrimaryKey
    val clothingId: String,
    val clothingName: String,
    val tagName: String
)