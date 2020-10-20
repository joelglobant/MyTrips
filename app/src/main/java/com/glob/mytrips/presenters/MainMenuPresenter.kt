package com.glob.mytrips.presenters

import com.glob.mytrips.app.DataInfoUser
import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.domain.providers.UserInfoProvider
import com.glob.mytrips.domain.usecases.userinfo.GetUserInfoUseCase
import com.glob.mytrips.models.mappers.UserMapperModel
import io.reactivex.disposables.CompositeDisposable

class MainMenuPresenter(
    private val userInfoProvider: UserInfoProvider,
    private val view: MainMenuContract.View
) : MainMenuContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getIdUser() {
        //TODO("Not yet implemented")
    }

    override fun getUserAccount(userId: Int) {
        val params = GetUserInfoUseCase.Params(userId)
        showLoader()
        disposable.add(
            userInfoProvider.getCountriesByUserUseCase().execute(params)
                .subscribe({success ->
                    val user = UserMapperModel().transform(success)
                    hideLoader()
                    DataInfoUser.getInstance().userInfo = user
                    view.onMainInfoLoaded(user)
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