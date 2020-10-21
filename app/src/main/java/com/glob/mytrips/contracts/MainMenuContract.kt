package com.glob.mytrips.contracts

import com.glob.mytrips.models.UserModel

interface MainMenuContract {

    interface View {
        fun showLoader(action: Boolean)
        fun openSettings()

        fun onMainInfoLoaded(userInfo: UserModel)
        fun onMainInfoLoadedFail(message: String)
    }

    interface Presenter {
        fun getUserAccount(userId: Int)
        fun onDestroy()
    }
}