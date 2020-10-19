package com.glob.mytrips.threadexecutor

import com.glob.mytrips.domain.executors.ThreadExecutor

class ImmediateThreadExecutor: ThreadExecutor {

    override fun execute(command: Runnable) {
        command.run()
    }
}