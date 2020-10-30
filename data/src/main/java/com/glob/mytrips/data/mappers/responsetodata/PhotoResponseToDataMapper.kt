package com.glob.mytrips.data.mappers.responsetodata

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.PhotoData
import com.glob.mytrips.data.remote.response.PhotoResponse

class PhotoResponseToDataMapper : Mapper<PhotoResponse, PhotoData> {
    override fun transform(value: PhotoResponse): PhotoData {
        return with(value) {
            PhotoData(id, idPlace, url)
        }
    }
}