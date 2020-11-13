package com.glob.mytrips.providers

import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.userinfo.GetUserInfoUseCase

interface UserInfoProvider {
    fun getUserUseCase(): SingleUseCase<GetUserInfoUseCase.Params, UserDto>
}