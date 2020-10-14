package com.glob.mytrips.app

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.UserDto

class DataInfoUser {
    companion object {
        @Volatile var INSTANCE: UserDto? = null
            get() = INSTANCE ?: synchronized(this) {
                    INSTANCE
                }
            set(value) {
                value?.let {
                    countries = it.generalPlaces
                }
                field = value
            }

        lateinit var countries: List<CountryDto>

    }
}