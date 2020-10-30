package com.glob.mytrips.data.local

import com.glob.mytrips.data.local.db.MyTripsDb
import com.glob.mytrips.data.local.entities.UserEntity
import com.glob.mytrips.data.repositories.datastore.cache.UserCache
import io.reactivex.Completable
import io.reactivex.Single

class UserLocalImpl(
    private val dataBase: MyTripsDb,
    private val preferencesHelper: PreferencesHelper
) : UserCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    override fun getUser(): Single<UserEntity> {
        return Single.just(dataBase.cachedUserDao().getCurrentUser())
    }

    override fun saveUser(user: UserEntity): Completable {
        return dataBase.cachedUserDao().saveNewUser(user)
    }

    override fun isCached(): Single<Boolean> {
       return Single.defer{
           Single.just(dataBase.cachedUserDao().getCurrentUser() != null)
        }
    }

    override fun setLastTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCachedTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    private fun getLastCachedTimeMillis() = preferencesHelper.lastCacheTime
}