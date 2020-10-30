package com.glob.mytrips.data.repositories.datastore

import com.glob.mytrips.domain.dtos.CountryDto
import io.reactivex.Completable
import io.reactivex.Single

interface CountryDataStore {
    fun getCountries(idUser: Int): Single<List<CountryDto>>
    fun saveCountry(country: CountryDto): Completable
    fun saveCountries(countries: List<CountryDto>): Completable
    fun isCached(idUser: Int): Single<Boolean>
}