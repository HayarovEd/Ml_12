package com.financialtracker.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.financialtracker.app.data.RepositoryAnalyticImpl
import com.financialtracker.app.data.RepositoryServerImpl
import com.financialtracker.app.data.ServiceImpl
import com.financialtracker.app.data.SharedKeeperImpl
import com.budgetwise.financial.domain.RepositoryAnalytic
import com.budgetwise.financial.domain.RepositoryServer
import com.budgetwise.financial.domain.Service
import com.budgetwise.financial.domain.SharedKepper
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {

    @Binds
    @Singleton
    abstract fun bindService(service: ServiceImpl): Service

    @Binds
    @Singleton
    abstract fun bindKeeper(sharedKeeper: SharedKeeperImpl): SharedKepper

    @Binds
    @Singleton
    abstract fun bindRepositoryAnalytic(repository: RepositoryAnalyticImpl): RepositoryAnalytic

    @Binds
    @Singleton
    abstract fun bindRepositoryServer(repository: RepositoryServerImpl): RepositoryServer

}