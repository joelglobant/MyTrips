package com.glob.mytrips.view.placelist.contacts

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.dtos.base.PlaceReference

interface PlaceListContracts {

    interface ViewCountries {
        fun setCountries(item: List<CountryDto>)
    }

    interface ViewStates {
        fun setStates(item: List<StateDto>)
    }

    interface ViewPlaces {
        fun setPlaces(item: List<PlaceDto>)
    }

    interface Presenter {
        fun unwrapList(list: List<PlaceReference>)
        fun setPlaces()
    }
}