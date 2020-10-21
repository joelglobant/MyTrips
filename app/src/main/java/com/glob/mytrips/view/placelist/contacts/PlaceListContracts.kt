package com.glob.mytrips.view.placelist.contacts

import com.glob.mytrips.models.CountryModel

interface PlaceListContracts {

    interface ViewCountries {
        fun setCountries(item: List<CountryModel>)
    }

    interface Presenter {
        fun setPlaces(generalList: List<CountryModel>)
    }
}