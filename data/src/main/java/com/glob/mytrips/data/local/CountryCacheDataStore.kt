package com.glob.mytrips.data.local

import com.glob.mytrips.data.mappers.datatodto.CountryDataToDtoMapper
import com.glob.mytrips.data.mappers.entitytodata.CountryEntityToDataMapper
import com.glob.mytrips.data.model.CountryData
import com.glob.mytrips.data.repositories.datastore.CountryDataStore
import com.glob.mytrips.data.repositories.datastore.cache.CountryCache
import com.glob.mytrips.domain.dtos.CountryDto
import io.reactivex.Completable
import io.reactivex.Single

class CountryCacheDataStore(
    private val countryCache: CountryCache,
    private val countryDataToDto: CountryDataToDtoMapper,
    private val countryEntToData: CountryEntityToDataMapper
) : CountryDataStore {
    override fun getCountries(idUser: Int): Single<List<CountryData>> {
        return countryCache.getCountres(idUser)
            .map { countries ->
                countries.map {
                    countryEntToData.transform(it)
                }
            }
    }

    override fun saveCountry(country: CountryDto): Completable {
        // TODO: 29/10/2020 verify the timeStamp
        val countryData = countryDataToDto.reverseTransform(country)
        return countryCache.saveCountry(countryEntToData.reverseTransform(countryData))
            .doOnComplete {
                println("Country saved!!! ")
            }
    }

    override fun saveCountries(countries: List<CountryDto>): Completable {
        val countriesData =
            countries.map { countryDataToDto.reverseTransform(it) }
        return countryCache.saveCountries(
            countriesData.map { countryEntToData.reverseTransform(it) }
        ).doOnComplete {
            println("Countries saved!!!!! ")
        }
    }

    override fun isCached(idUser: Int): Single<Boolean> {
        return countryCache.isCached(idUser)
    }
}