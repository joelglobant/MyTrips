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
class GetUserInfoUseCaseTest : TestCase() {

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
    fun `validate info from user registered`() {
        val params = GetUserInfoUseCase.Params(1)
        Mockito.`when`(userInfoRepository.getUserInfoById(1)).thenReturn(Single.just(MyTripsMocks().userMock))
        userInfoUseCase.execute(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.id == MyTripsMocks().userMock.id
            }
            .assertValue {
                it.name == MyTripsMocks().userMock.name
            }
            .assertValue {
                it.bio == MyTripsMocks().userMock.bio
            }
            .assertValue {
                it.generalPlaces.first().name == MyTripsMocks().userMock.generalPlaces.first().name
            }
    }

    @Test
    fun `validate error when a user didn't exists`() {
        val params = GetUserInfoUseCase.Params(10)
        val message = "Item not found"
        Mockito.`when`(userInfoRepository.getUserInfoById(10)).thenReturn(Single.error(Throwable(message)))
        userInfoUseCase.execute(params)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun `validate message error when params are null`() {
        val message = "Invalid Arguments"
        userInfoUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}