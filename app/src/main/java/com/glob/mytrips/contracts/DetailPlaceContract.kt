package com.glob.mytrips.contracts

import com.glob.mytrips.models.PlaceModel

interface DetailPlaceContract {

    interface View {
        fun setPlaceDetail(place: PlaceModel)

    }

    interface Presenter {

    }
}