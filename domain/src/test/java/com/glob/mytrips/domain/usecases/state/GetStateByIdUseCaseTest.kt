package com.glob.mytrips.domain.usecases.state

import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.repositories.StateRepository
import com.glob.mytrips.domain.usecases.ImmediateExecutorThread
import com.glob.mytrips.domain.usecases.mocks.MyTripsMocks
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class GetStateByIdUseCaseTest : TestCase() {

    @Mock
    lateinit var stateRepository: StateRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    private val stateByIdUseCase: GetStateByIdUseCase by lazy {
        GetStateByIdUseCase(
            stateRepository,
            ImmediateExecutorThread(), postExecutorThread
        )
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `validate get State By IdUsecase`() {
        val params = GetStateByIdUseCase.Params(1)
        Mockito.`when`(stateRepository.getStateByID(1))
            .thenReturn(Single.just(MyTripsMocks().stateMock))
        stateByIdUseCase.execute(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.id == MyTripsMocks().stateMock.id
            }
            .assertValue {
                it.name == MyTripsMocks().stateMock.name
            }
            .assertValue {
                it.places.first().id == MyTripsMocks().placeMock.id
            }
    }

    @Test
    fun `validate Message Error when State Not Registred`() {
        val params = GetStateByIdUseCase.Params(3)
        val message = "Item not Found"
        Mockito.`when`(stateRepository.getStateByID(3))
            .thenReturn(Single.error(Throwable(message)))
        stateByIdUseCase.execute(params)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate message Error With null parameters`() {
        val message = "Empty State list"
        stateByIdUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}