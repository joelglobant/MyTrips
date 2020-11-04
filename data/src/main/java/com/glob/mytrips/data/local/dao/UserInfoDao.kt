package com.glob.mytrips.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glob.mytrips.data.local.db.UserInfoConstants
import com.glob.mytrips.data.local.entities.UserEntity
import io.reactivex.Completable

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM ${UserInfoConstants.TABLE_USER}")
    fun getCurrentUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewUser(user: UserEntity): Completable
}