package com.glob.mytrips.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.glob.mytrips.data.local.db.UserInfoConstants
import com.glob.mytrips.data.local.entities.CountryEntity
import io.reactivex.Maybe

@Dao
interface CountryDao {
    @Query("SELECT * FROM ${UserInfoConstants.TABLE_COUNTRY} WHERE idUser=:idUser")
    fun getCountriesFromUser(idUser: Int): Maybe<List<CountryEntity>>

    @Insert
    fun saveNewCountry(countryEntity: CountryEntity)
}