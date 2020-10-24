package com.glob.mytrips.app

import com.glob.mytrips.models.PlaceModel
import com.glob.mytrips.models.UserModel

class DataInfoUser private constructor() {
    companion object {
        @Volatile
        private var instance: DataInfoUser? = null

        @Synchronized
        private fun createInstance() {
            if (instance == null)
                instance = DataInfoUser()
        }

        fun getInstance(): DataInfoUser {
            if (instance == null)
                createInstance()
            return instance!!
        }
    }

    lateinit var userInfo: UserModel

    var countryPosAt: Int = -1
    var statePosAt: Int = -1
    var placePosAt: Int = -1

    val comeFrom: String
        get() {
            return if (countryPosAt >= 0 && statePosAt >= 0)
                "${userInfo.generalPlaces[countryPosAt].states[statePosAt].name}, ${userInfo.generalPlaces[countryPosAt].name}"
            else
                ""
        }

    fun getPlace(at: Int): PlaceModel? {
        return if (userInfo != null && countryPosAt >= 0 && statePosAt >= 0)
            userInfo.generalPlaces[countryPosAt].states[statePosAt].places[at]
        else
            null
    }
}