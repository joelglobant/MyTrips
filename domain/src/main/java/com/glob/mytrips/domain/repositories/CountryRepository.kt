package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.CountryDto
import io.reactivex.Completable
import io.reactivex.Single

interface CountryRepository {
    fun getCountries(idUser: Int): Single<List<CountryDto>>
    fun saveCountries(countries: List<CountryDto>): Completable
}