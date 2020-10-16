package com.glob.mytrips.contracts

import com.glob.mytrips.domain.dtos.PlaceDto

interface DetailPlaceContract {

    interface View {
        fun setPlaceDetail(place: PlaceDto)

    }

    interface Presenter {

    }
}