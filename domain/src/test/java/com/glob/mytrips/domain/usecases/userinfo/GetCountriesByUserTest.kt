package com.glob.mytrips.domain.usecases.userinfo

import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.repositories.UserInfoRepository
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
class GetCountriesByUserTest : TestCase() {


    @Mock
    lateinit var userInfoRepository: UserInfoRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    private val userInfoUseCase: GetUserInfoUseCase by lazy {
        GetUserInfoUseCase(userInfoRepository,
            ImmediateExecutorThread(), postExecutorThread)
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun getCountriesSuccess() {
        val params = GetUserInfoUseCase.Params(1)
        Mockito.`when`(userInfoRepository.getCountriesByUser(1)).thenReturn(Single.just(listOf(MyTripsMocks().countryMock)))
        userInfoUseCase.execute(params)
            .map {
                it[0]
            }
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.id == MyTripsMocks().countryMock.id
            }
            .assertValue {
                it.name == MyTripsMocks().countryMock.name
            }
            .assertValue {
                it.states == MyTripsMocks().countryMock.states
            }
    }

    @Test
    fun getCountriesError() {
        val params = GetUserInfoUseCase.Params(1)
        val message = "Item not found"
        Mockito.`when`(userInfoRepository.getCountriesByUser(1)).thenReturn(Single.error(Throwable(message)))
        userInfoUseCase.execute(params)
            .map {
                it[0]
            }
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun getCountriesFail() {
        val message = "Invalid Arguments"
        userInfoUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}