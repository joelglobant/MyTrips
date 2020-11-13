package com.glob.mytrips.contracts

import com.glob.mytrips.models.StateModel

interface StateListContract {
    interface View {
        fun showLoader(action: Boolean)
        fun onStatesLoaded(states: List<StateModel>)
        fun onStatesLoadedFail(message: String)
    }

    interface Presenter {
        fun getStates(userId: Int)
        fun onDestroy()
    }
}