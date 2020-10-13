package com.glob.mytrips.domain.usecases.user

import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.UserRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetUserInfoUseCase(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetUserInfoUseCase.Params, UserDto> (
    threadExecutor,
    postExecutorThread
) {
    data class Params(val idUSer: Int)

    override fun buildSingleUseCase(params: Params?): Single<UserDto> {
        return params?.let {
            userRepository.getUserInfo(it.idUSer)
        } ?: Single.error(Throwable("User not found"))
    }
}