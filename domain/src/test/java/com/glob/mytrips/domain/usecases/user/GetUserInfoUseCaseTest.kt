package com.glob.mytrips.domain.usecases.user

import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.repositories.UserRepository
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
class GetUserInfoUseCaseTest : TestCase() {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    private val userInfoUseCase: GetUserInfoUseCase by lazy {
        GetUserInfoUseCase(
            userRepository,
            ImmediateExecutorThread(),
            postExecutorThread
        )
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun validateUserSuccess(){
        val params =  GetUserInfoUseCase.Params(1)
        Mockito.`when`(userRepository.getUserInfo(1)).thenReturn(Single.just(MyTripsMocks().userMock))
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
                it.nickName == MyTripsMocks().userMock.nickName
            }
            .assertValue {
                it.surname == MyTripsMocks().userMock.surname
            }
    }

    @Test
    fun validateUserError() {
        val params =  GetUserInfoUseCase.Params(1)
        val message = "User not found"
        Mockito.`when`(userRepository.getUserInfo(1)).thenReturn(Single.error(Throwable(message)))
        userInfoUseCase.execute(params)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

    @Test
    fun validateUserFail() {
        val message = "User not found"
        userInfoUseCase.execute(null)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }
}