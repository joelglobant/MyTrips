package com.glob.mytrips.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.glob.mytrips.data.local.db.UserInfoConstants
import com.glob.mytrips.data.local.entities.StateEntity
import io.reactivex.Maybe

@Dao
interface StateDao {
    @Query("SELECT * FROM ${UserInfoConstants.TABLE_STATE} WHERE idCountry =:idCountry")
    fun getStatesFromCountry(idCountry: Int): Maybe<List<StateEntity>>

    @Insert
    fun saveNewState(state: StateEntity)
}