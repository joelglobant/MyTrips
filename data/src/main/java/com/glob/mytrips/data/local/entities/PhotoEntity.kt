package com.glob.mytrips.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glob.mytrips.data.local.db.UserInfoConstants

@Entity(tableName = UserInfoConstants.TABLE_PHOTO)
data class PhotoEntity(
    @PrimaryKey
    val id: Int,
    val idPlace: Int,
    val url: String
)