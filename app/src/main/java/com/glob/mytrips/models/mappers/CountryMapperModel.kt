package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.models.CountryModel

class CountryMapperModel(private val stateMapperModel: StateMapperModel) : Transform<CountryDto, CountryModel>() {
    override fun transform(country: CountryDto): CountryModel {
        return with(country) {
            CountryModel(id, idUser, name, states.map {
                stateMapperModel.transform(it)
            })
        }
    }

    override fun reverseTransform(value: CountryModel): CountryDto {
        return with(value) {
            val stateList = kotlin.collections.arrayListOf<StateDto>()
            states.forEach { state ->
                stateList.add(
                    stateMapperModel.reverseTransform(state)
                )
            }
            CountryDto(id, idUser, name, stateList)
        }
    }
}