package com.glob.mytrips.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glob.mytrips.data.local.dao.CountryDao
import com.glob.mytrips.data.local.dao.PhotoDao
import com.glob.mytrips.data.local.dao.PlaceDao
import com.glob.mytrips.data.local.dao.StateDao
import com.glob.mytrips.data.local.entities.CountryEntity
import com.glob.mytrips.data.local.entities.PhotoEntity
import com.glob.mytrips.data.local.entities.PlaceEntity
import com.glob.mytrips.data.local.entities.StateEntity

@Database(
    entities = arrayOf(
        CountryEntity::class,
        StateEntity::class,
        PlaceEntity::class,
        PhotoEntity::class
        ),
    version = 1,
    exportSchema = false
)
abstract class MyTripsDb : RoomDatabase() {

    abstract fun cachedCountriesDao(): CountryDao
    abstract fun cachedStatesDao(): StateDao
    abstract fun cachedPlacesDao(): PlaceDao
    abstract fun cachedPhotosDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: MyTripsDb? = null

        fun getDatabase(context: Context): MyTripsDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    )
                        .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MyTripsDb::class.java, "MyTrips.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}