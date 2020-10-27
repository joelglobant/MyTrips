package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.response.PhotoResponse
import com.glob.mytrips.domain.dtos.PhotoDto

class PhotoMapper: Mapper<PhotoResponse, PhotoDto> {
    override fun transform(value: PhotoResponse): PhotoDto {
        return with(value) {
            PhotoDto(id, idPlace, url)
        }
    }
}