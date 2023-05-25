package com.countlesswrongs.cryptomonitor.di.module

import com.countlesswrongs.cryptomonitor.data.workers.ChildWorkerFactory
import com.countlesswrongs.cryptomonitor.data.workers.RefreshDataWorker
import com.countlesswrongs.cryptomonitor.di.key.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(refreshDataWorker: RefreshDataWorker.Factory): ChildWorkerFactory

}
