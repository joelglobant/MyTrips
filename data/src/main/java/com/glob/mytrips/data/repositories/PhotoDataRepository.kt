package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.mappers.responsetodto.UserResponseToDtoMapper
import com.glob.mytrips.data.remote.services.UserServices
import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.domain.repositories.PhotoRepository
import io.reactivex.Completable
import io.reactivex.Single

class PhotoDataRepository(private val userServices: UserServices,
                          private val userResponseToDtoMapper: UserResponseToDtoMapper
) /*: PhotoRepository {
    override fun getUserInfo(idUser: Int): Single<UserDto> {
        return userServices.getUser(idUser.toLong())
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let {
                        Single.just(userResponseToDtoMapper.transform(it))
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

}*/