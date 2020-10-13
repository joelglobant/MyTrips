package com.glob.mytrips.domain.usecases

import com.glob.mytrips.domain.executors.ThreadExecutor

class ImmediateExecutorThread : ThreadExecutor {

    override fun execute(runnable: Runnable) {
        runnable.run()
    }
}