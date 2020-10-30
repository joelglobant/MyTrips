package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.UserInfoDataStoryFactory
import com.glob.mytrips.data.local.entities.CountryEntity
import com.glob.mytrips.data.local.entities.UserEntity
import com.glob.mytrips.data.mappers.datatodto.UserDataToDtoMapper
import com.glob.mytrips.data.mappers.entitytodata.CountryEntityToDataMapper
import com.glob.mytrips.data.mappers.entitytodata.UserEntityToDataMapper
import com.glob.mytrips.data.model.CountryData
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.repositories.CountryRepository
import com.glob.mytrips.domain.repositories.UserInfoRepository
import io.reactivex.Completable
import io.reactivex.Single

class UserInfoDataRepository(
    private val factory: UserInfoDataStoryFactory,
    private val countryRepository: CountryRepository,
    private val userDataToDto: UserDataToDtoMapper,
    private val userEntityToDataMapper: UserEntityToDataMapper,
    private val countryEntityToData: CountryEntityToDataMapper
) : UserInfoRepository {

    override fun getUserInformation(): Single<UserDto> {
        return factory.retrieveLocalDataSource().isCached()
            .flatMap {
                factory.retrieveDataSource(it).getUser()
            }.flatMap { userResp ->
                saveCountries(userResp.countries)
                return@flatMap saveUser(userEntityToDataMapper.reverseTransform(userResp))
                    .toSingle { userResp }
            }.flatMap {
                Single.just(userDataToDto.transform(it))
            }
    }

    private fun saveUser(user: UserEntity): Completable {
        return factory.retrieveLocalDataSource().saveUser(user)
    }

    private fun saveCountries(countryList: List<CountryData>?) {
        countryList?.let { countries ->
            //countryRepository.saveCountries(countries.map { countryEntityToData.reverseTransform(it) })
        }
    }
}