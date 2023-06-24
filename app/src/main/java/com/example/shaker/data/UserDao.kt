package com.example.shaker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: User)

    @Query("SELECT EXISTS(SELECT 1 FROM user_table WHERE username = :username LIMIT 1)")
    suspend fun isUserExist(username: String): Boolean

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password LIMIT 1")
    fun getUser(username: String, password: String): Flow<User?>

}
