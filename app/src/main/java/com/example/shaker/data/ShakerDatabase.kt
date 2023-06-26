package com.example.shaker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shaker.data.dao.ClothingDao
import com.example.shaker.data.dao.OutfitClothingDao
import com.example.shaker.data.dao.OutfitDao
import com.example.shaker.data.dao.TagDao
import com.example.shaker.data.dao.UserDao
import com.example.shaker.data.tables.Clothing
import com.example.shaker.data.tables.Outfit
import com.example.shaker.data.tables.OutfitClothing
import com.example.shaker.data.tables.Tag
import com.example.shaker.data.tables.User

@Database(
    entities = [User::class, Clothing::class, Outfit::class, Tag::class, OutfitClothing::class],
    version = 1,
    exportSchema = false
)
abstract class ShakerDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun clothingDao(): ClothingDao
    abstract fun outfitDao(): OutfitDao
    abstract fun tagDao(): TagDao
    abstract fun outfitClothingDao(): OutfitClothingDao

    companion object {
        @Volatile
        private var INSTANCE: ShakerDatabase? = null

        fun getDatabase(context: Context): ShakerDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShakerDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
