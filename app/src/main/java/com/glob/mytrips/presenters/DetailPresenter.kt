package com.glob.mytrips.presenters

import com.glob.mytrips.app.DataInfoUser
import com.glob.mytrips.contracts.DetailPlaceContract

class DetailPresenter(private val view: DetailPlaceContract.View):
    DetailPlaceContract.Presenter {

    override fun getPlace(position: Int) {
        view.showLoader(true)
//        val modelRes = DataInfoUser.getInstance().getPlace(position)
//        if (modelRes != null)
//            view.setPlaceDetail(modelRes)
//        else
//            view.showError()
        view.showLoader(false)
    }
}