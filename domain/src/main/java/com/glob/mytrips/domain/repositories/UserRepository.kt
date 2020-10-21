package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.UserDto
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
    fun getUserInfo(idUser: Int): Single<UserDto>
    fun saveInformation(userEdited: UserDto): Completable
}