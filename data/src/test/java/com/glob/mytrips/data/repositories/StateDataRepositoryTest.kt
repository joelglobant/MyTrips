package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.StateDataStoreFactory
import com.glob.mytrips.data.mappers.datatodto.PlaceDataToDtoMapper
import com.glob.mytrips.data.mappers.datatodto.StateDataToDtoMapper
import com.glob.mytrips.data.mappers.responsetodto.PlaceResponseToDtoMapper
import com.glob.mytrips.data.mappers.responsetodto.StateResponseToDtoMapper
import com.glob.mytrips.data.mocks.MockPlacesHierarchy
import com.glob.mytrips.data.remote.services.StateServices
import com.glob.mytrips.domain.repositories.PlaceRepository
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class StateDataRepositoryTest : TestCase() {

    @Mock
    lateinit var stateServices: StateServices
    @Mock
    lateinit var factory: StateDataStoreFactory
    @Mock
    lateinit var placeRepository: PlaceRepository

    private val stateDataToDto = StateDataToDtoMapper()
    private val placeDataToDto = PlaceDataToDtoMapper()
    private val stateRespToDto = StateResponseToDtoMapper()

    private val stateDataRepository: StateDataRepository by lazy {
        StateDataRepository(factory, placeRepository, stateDataToDto, placeDataToDto)
    }

    @Test
    fun `validate get StatesByUser assert`() {
        val response = Response.success(listOf(MockPlacesHierarchy.stateResp))
        Mockito.`when`(stateServices.getStatesByCountry())
            .thenReturn(Single.just(response))
        stateDataRepository.getStatesByUser(1)
            .test()
            .assertComplete()
            .assertValue {
                it.first().id == MockPlacesHierarchy.stateResp.id
            }.assertValue {
                it.first().name == MockPlacesHierarchy.stateResp.name
            }.assertValue {
                it == listOf(stateRespToDto.transform(MockPlacesHierarchy.stateResp))
            }
    }

    @Test
    fun `validate get States By User error`() {
        val response = Response.success(MockPlacesHierarchy.stateResp)
        val message = "Something was wrong"
        Mockito.`when`(stateServices.getStatesByCountry()).thenReturn(Single.error(Throwable(message)))
        stateDataRepository.getStatesByUser(1)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate get State By Id`() {
        val response = Response.success(listOf(MockPlacesHierarchy.stateResp))
        Mockito.`when`(stateServices.getStatesByCountry()).thenReturn(Single.just(response))
        stateDataRepository.getStateByID(1)
            .test()
            .assertComplete()
            .assertValue {
                it.id == MockPlacesHierarchy.stateResp.id
            }.assertValue {
                it.name == MockPlacesHierarchy.stateResp.name
            }.assertValue {
                it == stateRespToDto.transform(MockPlacesHierarchy.stateResp)
            }
    }

    @Test
    fun `validate message error when user did'n found`() {
        val message = "Something was wrong"
        Mockito.`when`(stateServices.getStatesByCountry()).thenReturn(Single.error(Throwable(message)))
        stateDataRepository.getStateByID(1)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }
}