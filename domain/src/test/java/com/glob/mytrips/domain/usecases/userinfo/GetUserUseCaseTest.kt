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
class GetUserUseCaseTest : TestCase() {

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
        val trips = MyTripsMocks
        Mockito.`when`(userInfoRepository.getUserInformation()).thenReturn(Single.just(trips.userMock))
        userInfoUseCase.execute(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { // TODO: 18/11/2020 What's better, using this way or by group? ><
                it.id == trips.userMock.id
            }
            .assertValue {
                it.name == trips.userMock.name
            }
            .assertValue {
                it.bio == trips.userMock.bio
            }
            .assertValue {
                it.nickname== trips.userMock.nickname
            }
    }

    @Test
    fun `validate error when a user didn't exists`() {
        val params = GetUserInfoUseCase.Params(10)
        val message = "Item not found"
        Mockito.`when`(userInfoRepository.getUserInformation()).thenReturn(Single.error(Throwable(message)))
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