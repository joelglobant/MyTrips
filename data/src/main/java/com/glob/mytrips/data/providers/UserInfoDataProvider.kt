package com.glob.mytrips.data.providers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.providers.UserInfoProvider
import com.glob.mytrips.domain.repositories.UserInfoRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.userinfo.GetUserInfoUseCase

class UserInfoDataProvider(
    private val userInfoRepository: UserInfoRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutorThread: PostExecutorThread): UserInfoProvider {

    override fun getCountriesByUserUseCase(): SingleUseCase<GetUserInfoUseCase.Params, UserDto> {
        return GetUserInfoUseCase(userInfoRepository, threadExecutor, postExecutorThread)
    }

}