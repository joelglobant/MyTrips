package com.glob.mytrips.data.mappers

interface Mapper<T1, T2> {
    fun transform(value: T1): T2
    fun reverseTransform(value: T2): T1 = throw UnsupportedOperationException() //todo: help here!_ this is a valid argument??
}