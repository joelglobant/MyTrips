package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.mocks.MockPlacesHierarchy
import com.glob.mytrips.data.remote.response.PlaceResponse
import com.glob.mytrips.data.remote.services.PlaceServices
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class PlaceDataRepositoryTest : TestCase() {

    @Mock
    lateinit var placeServices: PlaceServices

    private val getPlacesCase: PlaceDataRepository by lazy {
        PlaceDataRepository(placeServices)
    }

    @Test
    fun `validate get Place By Id success`() {
        val idPlace = 1
        val placeResponse = PlaceResponse(1, "Jerez", emptyList(), "es magic", null, false)
        val response = Response.success(placeResponse)
        Mockito.`when`(placeServices.getPlaceDetail(idPlace)).thenReturn(Single.just(response))

        getPlacesCase.getPlaceById(idPlace)
            .test()
            .assertComplete()
            .assertValue {
                it.id == placeResponse.id
            }.assertValue {
                it.name == placeResponse.name
            }.assertValue {
                it.description == placeResponse.description
            }.assertValue {
                it.rank == placeResponse.rank
            }.assertValue {
                it.favorite == placeResponse.favorite
            }
    }

    @Test
    fun `validate get Place By Id with error`() {
        val idPlace = 23
        val message = "An error has occurred in the server"

        Mockito.`when`(placeServices.getPlaceDetail(idPlace))
            .thenReturn(Single.error(Throwable(message)))

        getPlacesCase.getPlaceById(idPlace)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate get all places by user`() {
        //3, mis parametros
        val response = Response.success(listOf(MockPlacesHierarchy.placeResponse))
        //2, que necesiro responder a mi operacion
        Mockito.`when`(placeServices.getPlacesByUser(MockPlacesHierarchy.idUser))
            .thenReturn(Single.just(response))
        //1, que operation voy a realizar
        getPlacesCase.getPlacesByUser(MockPlacesHierarchy.idUser)
            .test()
            .assertComplete()
            .assertValue {
                it.first().id == MockPlacesHierarchy.placeResponse.id
            }.assertValue {
                it.first().name == MockPlacesHierarchy.placeResponse.name
            }.assertValue {
                it.first().favorite == MockPlacesHierarchy.placeResponse.favorite
            }.assertValue {
                it.first().description == MockPlacesHierarchy.placeResponse.description
            }
    }

    @Test
    fun `validate error message when user didn't found`() {
        //3. mis parametros
        val message = "something was wrong"
        //2. que necesito responder a mi operacion
        Mockito.`when`(placeServices.getPlacesByUser(MockPlacesHierarchy.idUser))
            .thenReturn(Single.error(Throwable(message)))
        //1. que operacion voy a realizar
        getPlacesCase.getPlacesByUser(MockPlacesHierarchy.idUser)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}