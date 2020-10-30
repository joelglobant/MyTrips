package com.glob.mytrips.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glob.mytrips.data.local.db.UserInfoConstants

@Entity(tableName = UserInfoConstants.TABLE_USER)
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val nickname: String?,
    val surname: String?,
    val bio: String?
)