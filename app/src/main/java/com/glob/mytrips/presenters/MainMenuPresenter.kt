package com.glob.mytrips.presenters

import com.glob.mytrips.app.DataInfoUser
import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.domain.providers.UserInfoProvider
import com.glob.mytrips.domain.usecases.userinfo.GetUserInfoUseCase
import com.glob.mytrips.models.mappers.UserMapperModel
import io.reactivex.disposables.CompositeDisposable

class MainMenuPresenter(
    private val userInfoProvider: UserInfoProvider,
    private val view: MainMenuContract.View,
    private val userMapperModel: UserMapperModel
) : MainMenuContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getUserAccount(userId: Int) {
        val params = GetUserInfoUseCase.Params(userId)
        showLoader()
        disposable.add(
            userInfoProvider.getCountriesByUserUseCase().execute(params)
                .subscribe({success ->
                    hideLoader()
                    DataInfoUser.getInstance().userInfo = userMapperModel.transform(success)  // TODO: 21/10/2020 A what moment I fill my Singleto? it could be on a repository layer?
                    view.onMainInfoLoaded(userMapperModel.transform(success)) // TODO: 23/10/2020 this is a valid operation: pass object from domain to presentation layer?
                }, { throwable ->
                    view.onMainInfoLoadedFail(throwable.message ?: "")
                    hideLoader()
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