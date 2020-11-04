package com.glob.mytrips.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glob.mytrips.data.local.db.UserInfoConstants
import com.glob.mytrips.data.local.entities.PhotoEntity
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface PhotoDao {
    @Query("SELECT * FROM ${UserInfoConstants.TABLE_PHOTO} WHERE idPlace =:idPlace")
    fun getPhotosFromPlace(idPlace: Int): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewPhoto(photo: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewPhotos(photos: List<PhotoEntity>): Completable
}