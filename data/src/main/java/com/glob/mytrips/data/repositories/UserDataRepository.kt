package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.mappers.UserMapper
import com.glob.mytrips.data.remote.services.UserServices
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.repositories.UserRepository
import io.reactivex.Completable
import io.reactivex.Single

class UserDataRepository(private val userServices: UserServices) : UserRepository {
    override fun getUserInfo(idUser: Int): Single<UserDto> {
        return userServices.getUser(idUser.toLong())
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let {
                        Single.just(UserMapper().invoke(it))
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }

    override fun saveInformation(userEdited: UserDto): Completable {
        return userServices.saveUserByUser(1, userEdited)
            .flatMapCompletable { response ->
                return@flatMapCompletable if (response.isSuccessful) {
                    response.body()?.let {
                        if (it) {
                            Completable.complete()
                        } else {
                            Completable.error(Throwable(response.errorBody().toString()))
                        }
                    }
                } else {
                    Completable.error(Throwable(response.errorBody().toString()))
                }
            }
    }

}