package com.glob.mytrips.data.repositories.datastore.remote

import com.glob.mytrips.data.remote.response.CountryResponse
import io.reactivex.Single

interface CountryRemote {
        fun getCountries(idUser: Int): Single<List<CountryResponse>>
}