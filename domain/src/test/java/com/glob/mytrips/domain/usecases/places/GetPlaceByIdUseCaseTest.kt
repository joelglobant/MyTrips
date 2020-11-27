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
class GetPlaceByIdUseCaseTest : TestCase() {

    @Mock
    lateinit var placeRepository: PlaceRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    private val placeByIdUseCase: GetPlaceByIdUseCase by lazy {
        GetPlaceByIdUseCase(placeRepository,
            ImmediateExecutorThread(), postExecutorThread)
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `validate get Place by ID`() {
        val params = GetPlaceByIdUseCase.Params(1)
        val trypMock = MyTripsMocks
        Mockito.`when`(placeRepository.getPlace(1)).thenReturn(Single.just(trypMock.placeMock))
        placeByIdUseCase.execute(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.favorite == trypMock.placeMock.favorite
            }.assertValue {
                it.id == trypMock.placeMock.id
            }.assertValue {
                it.description == trypMock.placeMock.description
            }
    }

    @Test
    fun `validate Place Not Found`() {
    val params = GetPlaceByIdUseCase.Params(3)
        val message = "Place not found"
        Mockito.`when`(placeRepository.getPlace(3))
            .thenReturn(Single.error(Throwable(message)))
        placeByIdUseCase.execute(params)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate Error Message With null parameters`() {
        val message = "Place not found"
        placeByIdUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}