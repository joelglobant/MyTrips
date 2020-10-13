package com.glob.mytrips.domain.usecases.state

class SpyTest: IntestCap {

    fun doSomething (): String {
        return "hello"
    }

    fun doAnOperation(): Int {
        return 3
    }

    override fun doSomething(value: Int) {

    }

    override fun acarayEl16esmyCumple(edad: String) {
    }

    fun operationArgs(items: Int) = items.plus(1)
}