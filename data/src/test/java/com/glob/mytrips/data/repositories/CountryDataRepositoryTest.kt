package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.CountryDataStoreFactory
import com.glob.mytrips.data.mappers.datatodto.CountryDataToDtoMapper
import com.glob.mytrips.data.mappers.datatodto.StateDataToDtoMapper
import com.glob.mytrips.data.mocks.MockPlacesHierarchy
import com.glob.mytrips.data.remote.services.CountryServices
import com.glob.mytrips.domain.repositories.StateRepository
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
    lateinit var countryServices: CountryServices
    @Mock
    lateinit var factory : CountryDataStoreFactory
    @Mock
    lateinit var stateRepository: StateRepository

    private val countryDataToDto = CountryDataToDtoMapper()
    private val stateDataToDto = StateDataToDtoMapper()

    private val countryDataRepository: CountryDataRepository by lazy {
        CountryDataRepository(factory, stateRepository, countryDataToDto, stateDataToDto)
    }

    @Test
    fun `validate countries assert`() {
        val response = Response.success(listOf(MockPlacesHierarchy.countryResp))
        Mockito.`when`(countryServices.getCountriesByUser())
            .thenReturn(Single.just(response))
        countryDataRepository.getCountries(1)
            .test()
            .assertComplete()
            .assertValue {
                it.first().name == MockPlacesHierarchy.countryResp.name
            }.assertValue {
                it.first().id ==  MockPlacesHierarchy.countryResp.id
            }.assertValue {
                it.first().idUser == MockPlacesHierarchy.countryResp.idUser
            }
    }

    @Test
    fun `validate countries error`() {
        val message = "Something was wrong"
        Mockito.`when`(countryServices.getCountriesByUser())
            .thenReturn(Single.error(Throwable(message)))
        countryDataRepository.getCountries(1)
            .test()
            .assertNotComplete()
            .assertError {
                it.message == message
            }
    }
}