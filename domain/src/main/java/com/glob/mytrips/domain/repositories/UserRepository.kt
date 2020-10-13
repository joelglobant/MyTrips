package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.UserDto
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
    // TODO: 10/09/2020 it needs an entity
    //fun getUser(): Single<UserDto>
    fun getUserInfo(idUser: Int): Single<UserDto>
    fun saveInformation(userEdited: UserDto): Completable
}