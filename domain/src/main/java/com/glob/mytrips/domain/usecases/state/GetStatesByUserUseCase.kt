package com.glob.mytrips.domain.usecases.state

import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.StateRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetStatesByUserUseCase(
    private val stateRepository: StateRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetStatesByUserUseCase.Params, List<StateDto>>(
    threadExecutor,
    postExecutorThread
) {

    data class Params(val idUser: Int)

    override fun buildSingleUseCase(params: Params?): Single<List<StateDto>> {
        return params?.let {
            stateRepository.getStatesByUser(params.idUser)
        } ?: Single.error(Throwable("Empty list of trips"))
    }
}