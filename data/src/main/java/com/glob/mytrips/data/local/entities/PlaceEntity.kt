package com.glob.mytrips.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glob.mytrips.data.local.db.UserInfoConstants

@Entity(tableName = UserInfoConstants.TABLE_PLACE)
data class PlaceEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idState: Int,
    val name: String,
    val description: String,
    val rate: Double?,
    val favorite: Boolean
)