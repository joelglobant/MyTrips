package com.glob.mytrips.domain.usecases.state

import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.repositories.StateRepository
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
class GetStatesByUserUseCaseTest : TestCase() {

    public override fun setUp() {
        super.setUp()
    }

    @Mock
    lateinit var stateRepository: StateRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    val statesUseCase: GetStatesByUserUseCase by lazy {
        GetStatesByUserUseCase(stateRepository,
            ImmediateExecutorThread(), postExecutorThread)
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }


    @Test
    fun validateStateSuccess() {
        val params = GetStatesByUserUseCase.Params(1)
        val placeMock = PlaceDto(1, "jerez", listOf(PhotoDto("www.photo.com")), "",3.5, true)
        val stateMock = StateDto(1, "Zacatecas", listOf(placeMock))
        Mockito.`when`(stateRepository.getStatesByUser(1))
            .thenReturn(Single.just(listOf(stateMock)))
        statesUseCase.execute(params).map {
            it[0]
        }
            .test()
            .assertComplete()
            .assertNoErrors()
            // TODO: 05/09/2020 this case need to validate every value?
            .assertValue {
                it.id == stateMock.id
            }
            .assertValue {
                it.name == stateMock.name
            }
            .assertValue {
                it.places[0] == placeMock
            }
    }

    @Test
    fun validateGetStateError() {
        val params = GetStatesByUserUseCase.Params(1)
        val message = "Item not Found"
        Mockito.`when`(stateRepository.getStatesByUser(1))
            .thenReturn(Single.error(Throwable(message)))
        statesUseCase.execute(params)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun validateGetStateFail() {
        val message = "Empty list of trips"
        statesUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError{
                it.message == message
            }
    }
}