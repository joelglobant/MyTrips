package com.glob.mytrips.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glob.mytrips.data.local.db.UserInfoConstants

@Entity(tableName = UserInfoConstants.TABLE_COUNTRY)
data class CountryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idUser: Int,
    val name: String
)