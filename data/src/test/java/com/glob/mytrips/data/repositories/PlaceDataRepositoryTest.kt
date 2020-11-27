package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.PlaceDataStoreFactory
import com.glob.mytrips.data.mappers.datatodto.PhotoDataToDtoMapper
import com.glob.mytrips.data.mappers.datatodto.PlaceDataToDtoMapper
import com.glob.mytrips.data.mocks.MockPlacesHierarchy
import com.glob.mytrips.data.remote.services.PlaceServices
import com.glob.mytrips.domain.repositories.PhotoRepository
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

    @Mock
    lateinit var factory: PlaceDataStoreFactory

    @Mock
    lateinit var photoRepository: PhotoRepository

    private val placeDataToDto = PlaceDataToDtoMapper()
    private val photoDataToDto = PhotoDataToDtoMapper()

    private val getPlacesCase: PlaceDataRepository by lazy {
        PlaceDataRepository(factory, photoRepository, placeDataToDto, photoDataToDto)
    }

    @Test
    fun `validate get Place By Id success`() {
        val response = Response.success(MockPlacesHierarchy.placeResp)
        Mockito.`when`(placeServices.getPlaceById()).thenReturn(Single.just(response))
        getPlacesCase.getPlace(1)
            .test()
            .assertComplete()
            .assertValue {
                it.id == MockPlacesHierarchy.placeResp.id
            }.assertValue {
                it.name == MockPlacesHierarchy.placeResp.name
            }.assertValue {
                it.description == MockPlacesHierarchy.placeResp.description
            }.assertValue {
                it.rank == MockPlacesHierarchy.placeResp.rank
            }.assertValue {
                it.favorite == MockPlacesHierarchy.placeResp.favorite
            }
    }

    @Test
    fun `validate get Place By Id with error`() {
        val idPlace = 23
        val message = "An error has occurred in the server"
        Mockito.`when`(placeServices.getPlaceById())
            .thenReturn(Single.error(Throwable(message)))
        getPlacesCase.getPlace(idPlace)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate get all places by user`() {
        //3, mis parametros
        val response = Response.success(listOf(MockPlacesHierarchy.placeResp))
        //2, que necesiro responder a mi operacion
        Mockito.`when`(placeServices.getPlaceByState())
            .thenReturn(Single.just(response))
        //1, que operation voy a realizar
        getPlacesCase.getPlaces(1)
            .test()
            .assertComplete()
            .assertValue {
                it.first().id == MockPlacesHierarchy.placeResp.id
            }.assertValue {
                it.first().name == MockPlacesHierarchy.placeResp.name
            }.assertValue {
                it.first().favorite == MockPlacesHierarchy.placeResp.favorite
            }.assertValue {
                it.first().description == MockPlacesHierarchy.placeResp.description
            }
    }

    @Test
    fun `validate error message when user didn't found`() {
        //3. mis parametros
        val message = "something was wrong"
        //2. que necesito responder a mi operacion
        Mockito.`when`(placeServices.getPlaceByState())
            .thenReturn(Single.error(Throwable(message)))
        //1. que operacion voy a realizar
        getPlacesCase.getPlace(1)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}