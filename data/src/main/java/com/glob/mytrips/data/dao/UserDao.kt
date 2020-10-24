package com.glob.mytrips.data.dao

class UserDao {


    /**
     * // Repo data
    fun getCountriesByUser(userId: Int): Single<…> {
    return userDao.getCountriesByUser().map {
    mapper.transform(  it )  //transform to domain User
    }
    }
    // UserDao
    fun getCountriesByUser(…): Single<…> {
    return Single.just(DataUser(…))
    }
    **/
}