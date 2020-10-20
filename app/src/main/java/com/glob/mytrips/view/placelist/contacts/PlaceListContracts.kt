package com.glob.mytrips.view.placelist.contacts

//import com.glob.mytrips.domain.dtos.base.PlaceReference
import com.glob.mytrips.models.CountryModel
import com.glob.mytrips.models.PlaceModel
import com.glob.mytrips.models.StateModel

interface PlaceListContracts {

    interface ViewCountries {
        fun setCountries(item: List<CountryModel>)
    }

    interface ViewStates {
        fun setStates(item: List<StateModel>)
    }

    interface ViewPlaces {
        fun setPlaces(item: List<PlaceModel>)
    }

    interface Presenter {
        //fun unwrapList(list: List<PlaceReference>)
        fun setPlaces(generalList: List<CountryModel>)
    }
}