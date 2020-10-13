package com.glob.mytrips.data.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = arrayOf(), version = 1, exportSchema = false)
abstract class MyTripsDb : RoomDatabase() {

    //interface daos
    //abstract dao : Dao

    companion object {
        @Volatile private var INSTANCE: MyTripsDb? = null

        fun getDatabase(context: Context): MyTripsDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    )
                        .also { INSTANCE = it}
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MyTripsDb::class.java, "MyTrip.db")
                .fallbackToDestructiveMigration()
                .build()
    }

}