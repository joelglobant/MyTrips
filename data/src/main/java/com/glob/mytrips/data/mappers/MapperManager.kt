package com.glob.mytrips.data.mappers

abstract class MapperManager <T, U, V> {
    abstract fun mapFromService(type: T): U
    open fun mapToCache(type: U): T = throw UnsupportedOperationException()
    abstract fun mapToRequest(type: U): V
}