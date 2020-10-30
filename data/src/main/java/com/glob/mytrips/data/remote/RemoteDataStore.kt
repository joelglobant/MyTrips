package com.glob.mytrips.data.remote


import com.glob.mytrips.data.local.entities.UserEntity
import com.glob.mytrips.data.mappers.responsetodata.UserResponseToDataMapper
import com.glob.mytrips.data.model.UserData
import com.glob.mytrips.data.repositories.datastore.UserDataStore
import com.glob.mytrips.data.repositories.datastore.remote.UserRemote
import io.reactivex.Completable
import io.reactivex.Single

class RemoteDataStore(
    private val userRemote: UserRemote,
    private val userResponseToData: UserResponseToDataMapper
) : UserDataStore {

    override fun getUser(): Single<UserData> {
        return userRemote.getUserInfo()
            .flatMap {
                it.countries
                return@flatMap Single.just(userResponseToData.transform(it))
            }
    }

    override fun saveUser(user: UserEntity): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}