package com.glob.mytrips.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glob.mytrips.data.local.dao.*
import com.glob.mytrips.data.local.entities.*

@Database(
    entities = [UserEntity::class, CountryEntity::class, StateEntity::class, PlaceEntity::class, PhotoEntity::class],
    version = 3,
    exportSchema = false
)
abstract class MyTripsDb: RoomDatabase() {

    abstract fun cachedUserDao(): UserInfoDao
    abstract fun cachedCountriesDao(): CountryDao
    abstract fun cachedStatesDao(): StateDao
    abstract fun cachedPlacesDao(): PlaceDao
    abstract fun cachedPhotosDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: MyTripsDb? = null

        fun getInstance(context: Context): MyTripsDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context)
                    .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): MyTripsDb =
            Room.databaseBuilder(
                context.applicationContext,
                MyTripsDb::class.java, "MyTrips.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}