package com.glob.mytrips.data.providers

import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.StateRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.state.GetStateByIdUseCase
import com.glob.mytrips.domain.usecases.state.GetStatesByCountryUseCase

class StateDataProvider(
    private val stateRepository: StateRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutorThread: PostExecutorThread
) {
    fun getStateByIdUseCase(): SingleUseCase<GetStateByIdUseCase.Params, StateDto> {
        return GetStateByIdUseCase(stateRepository, threadExecutor, postExecutorThread)
    }

    fun getStatesByUserUseCase(): SingleUseCase<GetStatesByCountryUseCase.Params, List<StateDto>> {
        return GetStatesByCountryUseCase(stateRepository, threadExecutor, postExecutorThread)
    }
}