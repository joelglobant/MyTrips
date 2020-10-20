package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.models.CountryModel
import com.glob.mytrips.models.StateModel

class CountryMapperModel : Transform<CountryDto, CountryModel>() {
    override fun transform(country: CountryDto): CountryModel {
        return with(country) {
            val stateList = arrayListOf<StateModel>()
            states.forEach { state ->
                stateList.add(
                    StateMapperModel().transform(state)
                )
            }
            CountryModel(id, name, stateList)
        }
    }

    override fun reverseTransform(value: CountryModel): CountryDto {
        return with(value) {
            val stateList = kotlin.collections.arrayListOf<StateDto>()
            states.forEach { state ->
                stateList.add(
                    StateMapperModel().reverseTransform(state)
                )
            }
            CountryDto(id, name, stateList)
        }
    }
}