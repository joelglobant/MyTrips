package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.models.PhotoModel

class PhotoMapperModel : Transform<PhotoDto, PhotoModel>() {
    override fun transform(value: PhotoDto): PhotoModel {
        return with(value) {
            PhotoModel(id, idPlace, path)
        }
    }
}