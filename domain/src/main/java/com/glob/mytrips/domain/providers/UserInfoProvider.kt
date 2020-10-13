package com.glob.mytrips.domain.providers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.userinfo.GetUserInfoUseCase

interface UserInfoProvider {
    fun getCountriesByUserUseCase(): SingleUseCase<GetUserInfoUseCase.Params, UserDto>
}