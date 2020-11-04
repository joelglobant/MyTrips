package com.glob.mytrips.data.mappers.datatodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.PhotoData
import com.glob.mytrips.domain.dtos.PhotoDto

class PhotoDataToDtoMapper: Mapper<PhotoData, PhotoDto> {
    override fun transform(value: PhotoData): PhotoDto {
        return with(value) {
            PhotoDto(id, idPlace, url)
        }
    }

    override fun reverseTransform(value: PhotoDto): PhotoData {
        return with(value) {
            PhotoData(id, idPlace, path)
        }
    }
}