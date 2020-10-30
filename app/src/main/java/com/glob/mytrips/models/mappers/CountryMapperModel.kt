package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.models.CountryModel

class CountryMapperModel : Transform<CountryDto, CountryModel>() {
    override fun transform(country: CountryDto): CountryModel {
        return with(country) {
            CountryModel(id, name)
        }
    }

}