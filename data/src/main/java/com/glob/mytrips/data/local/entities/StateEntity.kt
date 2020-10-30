package com.glob.mytrips.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glob.mytrips.data.local.db.UserInfoConstants

@Entity(tableName = UserInfoConstants.TABLE_STATE)
data class StateEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idCountry: Int,
    val name: String
)