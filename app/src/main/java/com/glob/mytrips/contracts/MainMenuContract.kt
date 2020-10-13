package com.glob.mytrips.contracts

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.UserDto

interface MainMenuContract {

    interface View {
        fun showLoader(action: Boolean)
        fun openSettings()

        fun onMainInfoLoaded(userInfo: UserDto)
        fun onMainInfoLoadedFail(message: String)


        fun loadCounties(idUser: Int)
//        fun loadStates(idCounty: Int)
//        fun loadPlaces(idState: Int)
//        fun openPlace(idPlace: Int)
    }

    interface Presenter {
        fun getIdUser()
        fun getUserAccount(userId: Int)
//        fun getIdCountry(user: Int)
//        fun getIdState(county: Int)
//        fun getIdPlace(state: Int)
        fun onDestroy()
    }
}