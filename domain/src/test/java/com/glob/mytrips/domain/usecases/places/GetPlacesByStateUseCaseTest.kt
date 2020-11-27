package com.glob.mytrips.domain.usecases.places

import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.repositories.PlaceRepository
import com.glob.mytrips.domain.usecases.mocks.MyTripsMocks
import com.glob.mytrips.domain.usecases.ImmediateExecutorThread
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class GetPlacesByStateUseCaseTest : TestCase() {

    @Mock
    lateinit var placeRepository: PlaceRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    private val placeByStateUseCase: GetPlacesByStateUseCase by lazy {
        GetPlacesByStateUseCase(placeRepository,
            ImmediateExecutorThread(), postExecutorThread)
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `validate get Places by userId`() {
        val params = GetPlacesByStateUseCase.Params(1)
        val trypMock = MyTripsMocks
        Mockito.`when`(placeRepository.getPlaces(1)).thenReturn(Single.just(listOf(trypMock.placeMock)))
        placeByStateUseCase.execute(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.first().favorite == trypMock.placeMock.favorite
            }
            .assertValue {
                it.first().id == trypMock.placeMock.id
            }
            .assertValue {
                it.first().description == trypMock.placeMock.description
            }
    }

    @Test
    fun `validate Place Not Found`() {
        val params = GetPlacesByStateUseCase.Params(2)
        val message = "Invalid Arguments"
        Mockito.`when`(placeRepository.getPlaces(2))
            .thenReturn(Single.error(Throwable(message)))
        placeByStateUseCase.execute(params)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate Error Message With null parameters`() {
        val message = "Invalid Arguments"
        placeByStateUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}