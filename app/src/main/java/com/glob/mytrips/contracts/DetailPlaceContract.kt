package com.glob.mytrips.contracts

import com.glob.mytrips.models.PhotoModel
import com.glob.mytrips.models.PlaceModel

interface DetailPlaceContract {

    interface View {
        fun setPlaceDetail(place: PlaceModel)
        fun setPhotos(photos: List<PhotoModel>)
        fun showLoader(action: Boolean)
        fun showError()
    }

    interface Presenter {
        fun getCompletePlace(idPlace: Int)
    }
}