package com.glob.mytrips.domain.executors

import io.reactivex.Scheduler

interface PostExecutorThread {
    fun getScheduler() : Scheduler
}