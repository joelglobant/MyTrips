package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.mappers.responsetodto.StateResponseToDtoMapper
import com.glob.mytrips.data.remote.services.StateServices
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.repositories.StateRepository
import io.reactivex.Single

class StateDataRepository(
    private val stateServices: StateServices,
    private val stateResponseToDtoMapper: StateResponseToDtoMapper
)/* : StateRepository {

    override fun getStatesByUser(idUser: Int): Single<List<StateDto>> {
        return stateServices.getStatesByUser(idUser)
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    val stateList = arrayListOf<StateDto>()
                    response.body()?.let { list ->
                        list.forEach {
                            stateList.add(stateResponseToDtoMapper.transform(it))
                        }
                    }
                    Single.just(stateList)
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }

    override fun getStateByID(idState: Int): Single<StateDto> {
        return stateServices.getStateDetail(idState)
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let {
                        Single.just(stateResponseToDtoMapper.transform(it))
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }

}*/