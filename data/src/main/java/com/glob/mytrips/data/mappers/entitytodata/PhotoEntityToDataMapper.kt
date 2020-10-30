package com.glob.mytrips.data.mappers.entitytodata

import com.glob.mytrips.data.local.entities.PhotoEntity
import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.PhotoData

class PhotoEntityToDataMapper : Mapper<PhotoEntity, PhotoData> {
    override fun transform(value: PhotoEntity): PhotoData {
        return with(value) {
            PhotoData(id, idPlace, url)
        }
    }
}