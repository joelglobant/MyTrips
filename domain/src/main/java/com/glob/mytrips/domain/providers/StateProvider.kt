package com.glob.mytrips.domain.providers

import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.state.GetStateByIdUseCase
import com.glob.mytrips.domain.usecases.state.GetStatesByUserUseCase

interface StateProvider {
    fun getStateByIdUseCase(): SingleUseCase<GetStateByIdUseCase.Params, StateDto>
    fun getStatesByUserUseCase(): SingleUseCase<GetStatesByUserUseCase.Params, List<StateDto>>
}