package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.StateDataStoreFactory
import com.glob.mytrips.data.mappers.datatodto.PlaceDataToDtoMapper
import com.glob.mytrips.data.mappers.datatodto.StateDataToDtoMapper
import com.glob.mytrips.data.model.PlaceData
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.repositories.PlaceRepository
import com.glob.mytrips.domain.repositories.StateRepository
import io.reactivex.Completable
import io.reactivex.Single

class StateDataRepository(
    private val factory: StateDataStoreFactory,
    private val placeRepository: PlaceRepository,
    private val stateDataToDto: StateDataToDtoMapper,
    private val placeDataToDto: PlaceDataToDtoMapper
) : StateRepository {

    override fun getStatesByUser(idCountry: Int): Single<List<StateDto>> {
        return factory.retrieveLocalDataSource().isCached(idCountry)
            .flatMap {
                factory.retrieveDataSource(it).getStates(idCountry)
            }.flatMap { stateData ->
                stateData.forEach {
                    savePlaces(it.places)
                }
                return@flatMap saveStates(stateData.map { stateDataToDto.transform(it) })
                    .toSingle {
                        stateData.map {
                            stateDataToDto.transform(it)
                        }
                    }
            }
    }

    override fun getStateByID(idCountry: Int): Single<StateDto> {
        return factory.retrieveLocalDataSource().isCached(idCountry)
            .flatMap {
                factory.retrieveDataSource(it).getStates(idCountry)
            }.flatMap { statesData ->
                //fixme this is a temporal implementation!!
                return@flatMap Single.just(statesData.map { stateDataToDto.transform(it) }.first())
            }
    }

    override fun saveStates(states: List<StateDto>): Completable {
        return factory.retrieveLocalDataSource().saveStates(states)
    }

    private fun savePlaces(places: List<PlaceData>?) {
        places?.let { placesData ->
            placeRepository.savePlaces(placesData.map { placeDataToDto.transform(it) })
        }
    }

}