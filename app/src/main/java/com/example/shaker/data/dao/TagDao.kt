package com.example.shaker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shaker.data.tables.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTag(tag: Tag)

    @Query("SELECT * FROM tags")
    fun getTags(): Flow<Tag?>
}