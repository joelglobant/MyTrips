package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.UserInfoDataStoryFactory
import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.mappers.datatodto.CountryDataToDtoMapper
import com.glob.mytrips.data.mappers.datatodto.UserDataToDtoMapper
import com.glob.mytrips.data.mappers.entitytodata.UserEntityToDataMapper
import com.glob.mytrips.data.mocks.MockPlacesHierarchy
import com.glob.mytrips.data.remote.response.CountryResponse
import com.glob.mytrips.data.remote.services.UserServices
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.repositories.CountryRepository
import com.glob.mytrips.domain.repositories.UserInfoRepository
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class UserDataRepositoryTest {

    @Mock
    lateinit var userServices: UserServices

    @Mock
    lateinit var factory : UserInfoDataStoryFactory
    @Mock
    lateinit var countryRepository: CountryRepository

    private val userDataToDto = UserDataToDtoMapper()
    private val countryDataToDto = CountryDataToDtoMapper()
    private val userEntityToDataMapper = UserEntityToDataMapper()

    private lateinit var countryMapper: Mapper<CountryResponse, CountryDto>

    private val userDataRepository: UserInfoDataRepository by lazy {
        UserInfoDataRepository(factory, countryRepository, userDataToDto, countryDataToDto, userEntityToDataMapper)
    }

    @Test
    fun `validate countries assert`() {

        val response = Response.success(MockPlacesHierarchy.userResponse)

        Mockito.`when`(userServices.getCountriesByUser())
            .thenReturn(Single.just(response))
        userDataRepository.getUserInformation()
            .test()
            .assertComplete()
            .assertValue {
                it.surname == MockPlacesHierarchy.userResponse.surname
            }.assertValue {
                it.id ==  MockPlacesHierarchy.userResponse.id
            }.assertValue {
                it.bio == MockPlacesHierarchy.userResponse.bio
            }
    }

    @Test
    fun `validate countries error`() {
        val message = "Something was wrong"
        Mockito.`when`(userServices.getCountriesByUser())
            .thenReturn(Single.error(Throwable(message)))
        userDataRepository.getUserInformation()
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }
}