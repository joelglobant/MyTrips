package com.glob.mytrips.domain.providers

import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.user.GetUserInfoUseCase

interface UserProvider {
    fun getUserInfoUseCase(): SingleUseCase<GetUserInfoUseCase.Params, UserDto>
}