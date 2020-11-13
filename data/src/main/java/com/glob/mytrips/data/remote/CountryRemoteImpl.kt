package com.glob.mytrips.data.remote

import com.glob.mytrips.data.remote.response.CountryResponse
import com.glob.mytrips.data.remote.services.CountryServices
import com.glob.mytrips.data.repositories.datastore.remote.CountryRemote
import io.reactivex.Single

class CountryRemoteImpl(
    private val countryServices: CountryServices
) : CountryRemote {
    override fun getCountries(idUser: Int): Single<List<CountryResponse>> {
        return countryServices.getCountriesByUser()
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let { countryResponse ->
                        Single.just(countryResponse)
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }
    // TODO: 28/10/2020 create a multi selector for fake selectors
}