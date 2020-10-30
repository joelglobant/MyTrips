package com.glob.mytrips.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glob.mytrips.data.local.db.UserInfoConstants
import com.glob.mytrips.data.local.entities.PlaceEntity
import io.reactivex.Maybe

@Dao
interface PlaceDao {
    @Query("SELECT * FROM ${UserInfoConstants.TABLE_PLACE} WHERE idState =:idState")
    fun getPlacesFromState(idState: Int): Maybe<List<PlaceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewPlace(place: PlaceEntity)
}