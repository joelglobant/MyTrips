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
            // TODO: 27/10/2020 add a class selector to get an different id, aPI not completed!
            userInfoRepository.getUserInformation(/*it.idUser*/)
        } ?: Single.error(Throwable("Invalid Arguments"))
    }
}