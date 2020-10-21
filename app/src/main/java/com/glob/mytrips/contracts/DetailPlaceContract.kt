package com.glob.mytrips.contracts

import com.glob.mytrips.models.PlaceModel

interface DetailPlaceContract {

    interface View {
        fun setPlaceDetail(place: PlaceModel)
        fun showLoader(action: Boolean)
        fun showError()
    }

    interface Presenter {
        fun getPlace(position: Int)
    }
}