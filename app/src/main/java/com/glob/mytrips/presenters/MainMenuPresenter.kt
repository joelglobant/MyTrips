package com.glob.mytrips.presenters

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
            userInfoProvider.getUserUseCase().execute(params)
                .subscribe({success ->
                    hideLoader()
                    view.onMainInfoLoaded(userMapperModel.transform(success))
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