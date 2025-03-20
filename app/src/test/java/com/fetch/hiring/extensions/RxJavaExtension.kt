package com.fetch.hiring.extensions

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class RxJavaExtension:BeforeEachCallback , AfterEachCallback{

    companion object {
        val testScheduler = TestScheduler()
    }

    override fun beforeEach(context: ExtensionContext?) {
        reset()

        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler}

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler}
    }

    override fun afterEach(context: ExtensionContext?) {
        reset()
    }

    private fun reset() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}