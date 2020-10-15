package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.mappers.PlaceMapper
import com.glob.mytrips.data.mappers.StateMapper
import com.glob.mytrips.data.mocks.MockPlacesHierarchy
import com.glob.mytrips.data.remote.services.StateServices
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

    private val stateDataRepository: StateDataRepository by lazy {
        StateDataRepository(stateServices)
    }

    @Test
    fun `validate get StatesByUser assert`() {
        val response = Response.success(listOf(MockPlacesHierarchy.state))
        Mockito.`when`(stateServices.getStatesByUser(MockPlacesHierarchy.idUser))
            .thenReturn(Single.just(response))
        stateDataRepository.getStatesByUser(MockPlacesHierarchy.idUser)
            .test()
            .assertComplete()
            .assertValue {
                it.first().id == MockPlacesHierarchy.state.id
            }.assertValue {
                it.first().name == MockPlacesHierarchy.state.name
            }.assertValue {
                it == listOf(StateMapper().invoke(MockPlacesHierarchy.state))
            }
    }

    @Test
    fun `validate get States By User error`() {
        val response = Response.success(MockPlacesHierarchy.state)
        val message = "Something was wrong"
        Mockito.`when`(stateServices.getStatesByUser(MockPlacesHierarchy.idUser)).thenReturn(Single.error(Throwable(message)))
        stateDataRepository.getStatesByUser(MockPlacesHierarchy.idUser)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate get State By Id`() {
        val response = Response.success(MockPlacesHierarchy.state)
        Mockito.`when`(stateServices.getStateDetail(MockPlacesHierarchy.idState)).thenReturn(Single.just(response))
        stateDataRepository.getStateByID(MockPlacesHierarchy.idState)
            .test()
            .assertComplete()
            .assertValue {
                it.id == MockPlacesHierarchy.state.id
            }.assertValue {
                it.name == MockPlacesHierarchy.state.name
            }.assertValue {
                it.places == listOf(PlaceMapper().invoke(MockPlacesHierarchy.placeResponse))
            }
    }

    @Test
    fun `validate message error when user did'n found`() {
        val message = "Something was wrong"
        Mockito.`when`(stateServices.getStateDetail(MockPlacesHierarchy.idState)).thenReturn(Single.error(Throwable(message)))
        stateDataRepository.getStateByID(MockPlacesHierarchy.idState)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}