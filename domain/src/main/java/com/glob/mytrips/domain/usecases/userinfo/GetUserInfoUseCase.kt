package com.glob.mytrips.domain.usecases.userinfo

import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.UserInfoRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetUserInfoUseCase(
    private val userInfoRepository: UserInfoRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetUserInfoUseCase.Params, UserDto>(
    threadExecutor,
    postExecutorThread
) {

    data class Params(val idUser: Int)

    override fun buildSingleUseCase(params: Params?): Single<UserDto> {
        return params?.let {
            userInfoRepository.getUserInfoById(it.idUser)
        } ?: Single.error(Throwable("Invalid Arguments"))
    }
}