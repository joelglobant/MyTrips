package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.mappers.CountryMapper
import com.glob.mytrips.data.mocks.MockPlacesHierarchy
import com.glob.mytrips.data.remote.services.UserInfoServices
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class CountryDataRepositoryTest : TestCase() {

    @Mock
    lateinit var userInfoServices: UserInfoServices

    private val countryDataRepository: UserInfoDataRepository by lazy {
        UserInfoDataRepository(userInfoServices)
    }

    @Test
    fun `validate countries assert`() {

        val response = Response.success(listOf(MockPlacesHierarchy.country))

        Mockito.`when`(userInfoServices.getCountriesByUser(MockPlacesHierarchy.idUser))
            .thenReturn(Single.just(response))
        countryDataRepository.getCountriesByUser(MockPlacesHierarchy.idUser)
            .test()
            .assertComplete()
            .assertValue {
                it == listOf(CountryMapper().invoke(MockPlacesHierarchy.country))
            }
    }

    @Test
    fun `validate countries error`() {
        val message = "Something was wrong"
        Mockito.`when`(userInfoServices.getCountriesByUser(MockPlacesHierarchy.idUser))
            .thenReturn(Single.error(Throwable(message)))
        countryDataRepository.getCountriesByUser(MockPlacesHierarchy.idUser)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }

}