package com.glob.mytrips.data.local

import com.glob.mytrips.data.local.db.MyTripsDb
import com.glob.mytrips.data.local.entities.CountryEntity
import com.glob.mytrips.data.repositories.datastore.cache.CountryCache
import io.reactivex.Completable
import io.reactivex.Single

class CountryCacheImpl(
    private val database: MyTripsDb
): CountryCache {

    override fun getCountres(idUser: Int): Single<List<CountryEntity>> {
        return Single.just(database.cachedCountriesDao().getCountriesFromUser(idUser))
    }

    override fun saveCountry(country: CountryEntity): Completable {
        return database.cachedCountriesDao().saveNewCountry(country)
    }

    override fun saveCountries(countries: List<CountryEntity>): Completable {
        return database.cachedCountriesDao().saveNewCountries(countries)
    }

    override fun isCached(idUser: Int): Single<Boolean> {
        return Single.defer {
            Single.just(database.cachedCountriesDao().getCountriesFromUser(idUser).isNotEmpty())
        }
    }

    override fun isExpired(): Boolean {
        // TODO: 29/10/2020 here apply a different logic to time expired!
        return false
    }
}