package com.glob.mytrips.data.executors

import com.glob.mytrips.domain.executors.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class JobExecutor: ThreadExecutor {

    companion object {
        private const val CORE_POOL_SIZE = 3
        private const val MAXIMUM_POOL_SIZE = 5
        private const val KEEP_ALIVE_TIME = 10L
        private const val BASE_NAME_THREAD = "android_"
    }

    private val threadPoolExecutor by lazy {
        ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
        LinkedBlockingQueue<Runnable>(), JobThreadFactory())
    }

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory: ThreadFactory {
        private var counter = 0
        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, BASE_NAME_THREAD + counter++)
        }
    }
}