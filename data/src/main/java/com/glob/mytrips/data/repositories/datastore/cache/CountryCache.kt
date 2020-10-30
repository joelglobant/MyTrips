package com.glob.mytrips.data.repositories.datastore.cache

import com.glob.mytrips.data.local.entities.CountryEntity
import io.reactivex.Completable
import io.reactivex.Single

interface CountryCache {
    fun getCountres(idUser: Int) : Single<List<CountryEntity>>
    fun saveCountry(country: CountryEntity): Completable
    fun saveCountries(countries: List<CountryEntity>): Completable
    fun isCached(idUser: Int): Single<Boolean>
    //fun setLastTime(lastCache: Long)
    fun isExpired(): Boolean
}