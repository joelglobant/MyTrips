package com.glob.mytrips.presenters

import com.glob.mytrips.contracts.CountryListContract
import com.glob.mytrips.data.providers.CountryDataProvider
import com.glob.mytrips.domain.usecases.country.GetCountryUseCase
import com.glob.mytrips.models.mappers.CountryMapperModel
import io.reactivex.disposables.CompositeDisposable

class CountryListPresenter(
    private val view: CountryListContract.View,
    private val countryProvider: CountryDataProvider,
    private val countryMapper: CountryMapperModel
) : CountryListContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getCountries(userId: Int) {
        val params = GetCountryUseCase.Params(userId)
        showLoader()
        disposable.add(
            countryProvider.getCountryUseCase().execute(params)
                .subscribe({ success ->
                    hideLoader()
                    view.onCountryLoaded(success.map { countryMapper.transform(it) })
                }, { throwable ->
                    hideLoader()
                    view.onCountryLoadedFail(throwable.message ?: "")
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