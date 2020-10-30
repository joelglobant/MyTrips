package com.glob.mytrips.contracts

import com.glob.mytrips.models.CountryModel

interface CountryListContract {
    interface View {
        fun showLoader(action: Boolean)
        fun onCountryLoaded(countries: List<CountryModel>)
        fun onCountryLoadedFail(message: String)
    }

    interface Presenter {
        fun getCountries(userId: Int)
        fun onDestroy()
    }
}