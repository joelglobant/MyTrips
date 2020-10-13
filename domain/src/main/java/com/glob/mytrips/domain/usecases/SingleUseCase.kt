package com.glob.mytrips.domain.usecases

import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<INPUT, OUTPUT : Any> (
    private val threadExecutor: ThreadExecutor,
    private val postExecutorThread: PostExecutorThread
){
    abstract fun buildSingleUseCase(params: INPUT? = null): Single<OUTPUT>

    fun execute(params: INPUT?): Single<OUTPUT> {
        return this.buildSingleUseCase(params).subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutorThread.getScheduler())
    }
}