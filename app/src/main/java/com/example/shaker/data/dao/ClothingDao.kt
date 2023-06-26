package com.example.shaker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shaker.data.tables.Clothing
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addClothing(clothing: Clothing)

    @Query("SELECT * FROM clothing")
    fun getAllCloths(): Flow<List<Clothing>?>

    @Query("SELECT * FROM clothing WHERE clothingName = :clothingName")
    fun getClothingByName(clothingName: String): Flow<Clothing?>
}