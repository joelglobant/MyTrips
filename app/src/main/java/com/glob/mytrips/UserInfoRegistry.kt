package com.glob.mytrips

import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.data.executors.JobExecutor
import com.glob.mytrips.data.executors.UIThread
import com.glob.mytrips.data.providers.UserInfoDataProvider
import com.glob.mytrips.services.RetrofitFactory
import com.glob.mytrips.data.remote.services.UserInfoServices
import com.glob.mytrips.data.repositories.UserInfoDataRepository
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.providers.UserInfoProvider
import com.glob.mytrips.domain.repositories.UserInfoRepository
import com.glob.mytrips.presenters.MainMenuPresenter

class UserInfoRegistry {

    private val threadExecutor: ThreadExecutor by lazy {
        JobExecutor()
    }

    private val postExecutorThread: PostExecutorThread by lazy {
        UIThread()
    }

    private fun getUserService(): UserInfoServices {
        return RetrofitFactory.instance().create(UserInfoServices::class.java)
    }

    private val userRepository: UserInfoRepository by lazy {
        UserInfoDataRepository(getUserService())
    }

    private val provider: UserInfoProvider by lazy {
        UserInfoDataProvider(userRepository, threadExecutor, postExecutorThread)
    }

    fun provide(view: MainMenuContract.View): MainMenuContract.Presenter {
        return MainMenuPresenter(provider, view)
    }

}