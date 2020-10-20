package com.glob.mytrips.models.mappers

abstract class Transform <T1, T2> {
    abstract fun transform(value: T1): T2
    open fun reverseTransform(value: T2): T1 = throw UnsupportedOperationException()
}