package com.glob.mytrips.data.mappers

abstract class MapperManager <T, U, V> {
    abstract fun mapFromService(type: T): U
    //fun mapToCache(type: U): T
    abstract fun mapToRequest(type: U): V
}