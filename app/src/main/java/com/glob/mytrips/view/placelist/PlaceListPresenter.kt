package com.glob.mytrips.view.placelist

import com.glob.mytrips.models.CountryModel
import com.glob.mytrips.view.placelist.contacts.PlaceListContracts

class PlaceListPresenter(val view: PlaceListContracts.ViewCountries): PlaceListContracts.Presenter {
    private lateinit var places: List<CountryModel>

    override fun setPlaces(generalList: List<CountryModel>) {
       this.places = generalList
    }
}