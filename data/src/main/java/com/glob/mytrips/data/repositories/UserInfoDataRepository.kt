package com.glob.mytrips.data.repositories

import android.util.Log
import com.glob.mytrips.data.mappers.CountryMapper
import com.glob.mytrips.data.remote.services.UserInfoServices
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.repositories.UserInfoRepository
import io.reactivex.Single

class UserInfoDataRepository(private val userInfoServices: UserInfoServices) : UserInfoRepository {

    override fun getCountriesByUser(idUser: Int): Single<UserDto> {
        return userInfoServices.getCountriesByUser()
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let { userResponse ->
                        val countryList = arrayListOf<CountryDto>()
                        with(userResponse){
                            countries.forEach {
                                countryList.add(CountryMapper().invoke(it))
                            }
                            val userInfo = UserDto(id, name, nickname, surname, bio, countryList)
                            Single.just(userInfo)
                        }
                    }
                } else {
                    Log.e("UserInfoDataRepository", "getCountriesByUser: ")
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }

}