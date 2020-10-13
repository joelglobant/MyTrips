package com.glob.mytrips.domain.usecases.state

import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.StateRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetStateByIdUseCase(
    private val stateRepository: StateRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetStateByIdUseCase.Params, StateDto>(
    threadExecutor,
    postExecutorThread
) {

    data class Params(val idState: Int)

    override fun buildSingleUseCase(params: Params?): Single<StateDto> {
        return params?.let {
            stateRepository.getStateByID(it.idState)
        } ?: Single.error(Throwable("Empty State list"))
    }
}