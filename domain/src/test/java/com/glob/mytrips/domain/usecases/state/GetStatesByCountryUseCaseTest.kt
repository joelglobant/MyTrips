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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class GetStatesByCountryUseCaseTest : TestCase() {

    @Mock
    lateinit var stateRepository: StateRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    val statesUseCase: GetStatesByCountryUseCase by lazy {
        GetStatesByCountryUseCase(stateRepository,
            ImmediateExecutorThread(), postExecutorThread)
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `validate get States By Usercase`() {
        val params = GetStatesByCountryUseCase.Params(1)
        val trypMock = MyTripsMocks
        Mockito.`when`(stateRepository.getStatesByUser(1))
            .thenReturn(Single.just(listOf(trypMock.stateMock)))
        statesUseCase.execute(params).map {
            it[0]
        }
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.id == trypMock.stateMock.id
            }.assertValue {
                it.name == trypMock.stateMock.name
            }.assertValue {
                it == trypMock.stateMock
            }
    }

    @Test
    fun `validate error message when user didn't found`() {
        val params = GetStatesByCountryUseCase.Params(1)
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
    fun `validate error message with null parameters`() {
        val message = "Empty list of trips"
        statesUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError{
                it.message == message
            }
    }
}