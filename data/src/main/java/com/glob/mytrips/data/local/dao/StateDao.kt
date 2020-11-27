package com.glob.mytrips.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glob.mytrips.data.local.db.UserInfoConstants
import com.glob.mytrips.data.local.entities.StateEntity
import io.reactivex.Completable

@Dao
interface StateDao {
    @Query("SELECT * FROM ${UserInfoConstants.TABLE_STATE} WHERE idCountry =:idCountry")
    fun getStatesFromCountry(idCountry: Int): List<StateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewState(state: StateEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewStates(states: List<StateEntity>): Completable
}