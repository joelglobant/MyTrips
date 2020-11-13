package com.glob.mytrips.contracts

import com.glob.mytrips.models.PlaceModel

interface PlaceContract {
    interface View {
        fun showLoader(action: Boolean)
        fun onPlacesLoaded(places: List<PlaceModel>)
        fun onPlacesLoadedFail(message: String)
    }

    interface Presenter {
        fun getPlaces(idState: Int)
        fun onDestroy()
    }
}