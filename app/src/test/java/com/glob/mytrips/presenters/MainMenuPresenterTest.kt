package com.glob.mytrips.presenters

import com.glob.mytrips.contracts.MainMenuContract
import com.glob.mytrips.data.mocks.MockPlaces
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.providers.UserInfoProvider
import com.glob.mytrips.domain.repositories.UserInfoRepository
import com.glob.mytrips.domain.usecases.userinfo.GetUserInfoUseCase
import com.glob.mytrips.threadexecutor.ImmediateThreadExecutor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.Response
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class MainMenuPresenterTest {

    @Mock
    lateinit var view: MainMenuContract.View

    @Mock
    lateinit var provider: UserInfoProvider

    @Mock
    lateinit var repository: UserInfoRepository

    @Mock
    private lateinit var postExecutorThread: PostExecutorThread

    private val presenter: MainMenuPresenter by lazy {
        MainMenuPresenter(provider, view)
    }

    @Before
    fun setup() {
        Mockito.`when`(postExecutorThread.getScheduler()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `validate get user information by id`() {
        val userInfoResp = MockPlaces.userResponse
        val countryList = arrayListOf<CountryDto>()
        val userInfo = with(userInfoResp){
            countries.forEach {
                countryList.add(com.glob.mytrips.data.mappers.CountryMapper().invoke(it))
            }
            UserDto(id, name, nickname, surname, bio, countryList)
        }

        Mockito.`when`(repository.getUserInfoById(1)).thenReturn(Single.just(userInfo))
        Mockito.`when`(provider.getCountriesByUserUseCase())
            .thenReturn(GetUserInfoUseCase(repository, ImmediateThreadExecutor(), postExecutorThread))
        presenter.getUserAccount(1)
        Mockito.verify(view).onMainInfoLoaded(userInfo)
    }
}