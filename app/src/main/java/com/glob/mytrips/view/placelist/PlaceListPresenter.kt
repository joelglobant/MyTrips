package com.glob.mytrips.view.placelist

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.base.PlaceReference
import com.glob.mytrips.view.placelist.contacts.PlaceListContracts

// TODO: 13/10/2020 Create a General View for any Place
class PlaceListPresenter(val view: PlaceListContracts.ViewCountries): PlaceListContracts.Presenter {
    private lateinit var places: List<CountryDto>

    override fun unwrapList(list: List<PlaceReference>) {
        //TODO("Not yet implemented")
    }

    override fun setPlaces(generalList: List<CountryDto>) {
       this.places = generalList
    }


}