package com.glob.mytrips.data.executors

import com.glob.mytrips.domain.executors.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread: PostExecutorThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}