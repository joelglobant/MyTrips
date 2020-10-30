package com.glob.mytrips.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    companion object {
        private const val PREF_USER_PACKAGE_NAME = "com.glob.mytrips.data.preferences"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }
    private val userPreferences: SharedPreferences

    init {
        userPreferences = context.getSharedPreferences(PREF_USER_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = userPreferences.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = userPreferences.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}