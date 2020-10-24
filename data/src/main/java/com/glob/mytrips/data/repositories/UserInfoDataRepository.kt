package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.mappers.CountryMapper
import com.glob.mytrips.data.mappers.UserMapper
import com.glob.mytrips.data.remote.services.UserInfoServices
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.repositories.UserInfoRepository
import io.reactivex.Single

class UserInfoDataRepository(
    private val userInfoServices: UserInfoServices,
    private val userMapper: UserMapper
) : UserInfoRepository {
    //fwefawmerlk
    //return dao...//userInfoServices.getCountriesByUser()
    override fun getUserInfoById(idUser: Int): Single<UserDto> {
        return userInfoServices.getCountriesByUser()
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let { userResponse ->
                        Single.just(userMapper.transform(userResponse))
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }

}