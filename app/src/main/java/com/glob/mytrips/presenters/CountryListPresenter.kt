package com.glob.mytrips.presenters

import com.glob.mytrips.contracts.CountryListContract
import com.glob.mytrips.domain.providers.CountryProvider

class CountryListPresenter(
    private val view: CountryListContract.View,
    private val countryCountryProvider: CountryProvider
) : CountryListContract.Presenter {

    override fun getCountries(userId: Int) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

}