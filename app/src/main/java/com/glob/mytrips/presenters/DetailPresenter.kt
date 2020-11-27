package com.glob.mytrips.presenters

import com.glob.mytrips.contracts.DetailPlaceContract
import com.glob.mytrips.data.providers.PhotoDataProvider
import com.glob.mytrips.data.providers.PlaceDataProvider
import com.glob.mytrips.domain.usecases.GetPhotosUseCase
import com.glob.mytrips.domain.usecases.places.GetPlaceByIdUseCase
import com.glob.mytrips.models.mappers.PhotoMapperModel
import com.glob.mytrips.models.mappers.PlaceMapperModel
import io.reactivex.disposables.CompositeDisposable

class DetailPresenter(
    private val placeProvider: PlaceDataProvider,
    private val photoProvider: PhotoDataProvider,
    private val view: DetailPlaceContract.View,
    private val placeMapper: PlaceMapperModel,
    private val photoMapper: PhotoMapperModel
) : DetailPlaceContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getCompletePlace(idPlace: Int) {
        view.showLoader(true)
        val placeParams = GetPlaceByIdUseCase.Params(idPlace)
        disposable.add(
            placeProvider.getPlaceByIdUseCase().execute(placeParams)
                .subscribe({ success ->
                    view.setPlaceDetail(placeMapper.transform(success))
                }, {
                    //TODO() add an error message
                })
        )
        val photoParams = GetPhotosUseCase.Params(idPlace)
        disposable.add(
            photoProvider.getPhotosByPlaceUseCase().execute(photoParams)
                .subscribe({ success ->
                    view.setPhotos(success.map { photoMapper.transform(it) })
                    view.showLoader(false)
                }, { throwable ->
                    // TODO: 13/11/2020 add an error message to show
                    view.showError()
                    view.showLoader(false)
                })
        )
    }

    override fun onDestroy() {
        disposable.dispose()
    }
}