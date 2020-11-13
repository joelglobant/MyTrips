package com.glob.mytrips.presenters

import com.glob.mytrips.contracts.StateListContract
import com.glob.mytrips.data.providers.StateDataProvider
import com.glob.mytrips.domain.usecases.state.GetStatesByCountryUseCase
import com.glob.mytrips.models.mappers.StateMapperModel
import io.reactivex.disposables.CompositeDisposable

class StateListPresenter(
    private val stateProvider: StateDataProvider,
    private val view: StateListContract.View,
    private val stateModelMap: StateMapperModel
) : StateListContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getStates(userId: Int) {
        val params = GetStatesByCountryUseCase.Params(userId)
        showLoader()
        disposable.add(
            stateProvider.getStatesByUserUseCase().execute(params)
                .subscribe({ success ->
                    hideLoader()
                    view.onStatesLoaded(success.map { stateModelMap.transform(it) })
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