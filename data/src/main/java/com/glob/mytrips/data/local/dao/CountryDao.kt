package com.glob.mytrips.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glob.mytrips.data.local.db.UserInfoConstants
import com.glob.mytrips.data.local.entities.CountryEntity
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface CountryDao {
    @Query("SELECT * FROM ${UserInfoConstants.TABLE_COUNTRY} WHERE idUser=:idUser")
    fun getCountriesFromUser(idUser: Int): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewCountry(countryEntity: CountryEntity): Completable

    // TODO: 29/10/2020 it is ok?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewCountries(countries: List<CountryEntity>): Completable
}