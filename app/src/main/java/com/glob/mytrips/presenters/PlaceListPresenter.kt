package com.glob.mytrips.presenters

import com.glob.mytrips.contracts.PlaceContract
import com.glob.mytrips.data.providers.PlaceDataProvider
import com.glob.mytrips.domain.usecases.places.GetPlacesByStateUseCase
import com.glob.mytrips.models.mappers.PlaceMapperModel
import io.reactivex.disposables.CompositeDisposable

class PlaceListPresenter(
    private val placeDataProvider: PlaceDataProvider,
    private val view: PlaceContract.View,
    private val placeModelMap: PlaceMapperModel
): PlaceContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getPlaces(idState: Int) {
        val params = GetPlacesByStateUseCase.Params(idState)
        showLoader()
        disposable.add(
            placeDataProvider.getPlacesUseCase().execute(params)
                .subscribe({ success ->
                    hideLoader()
                    view.onPlacesLoaded(success.map { placeModelMap.transform(it) })
                }, {
                    hideLoader()
                    //
                })
        )
    }

    private fun showLoader() {
        view.showLoader(true)
    }

    private fun hideLoader() {
        view.showLoader(false)
    }

    override fun onDestroy() {
        disposable.dispose()
    }
}