package com.solvathon.di

import com.solvathon.domain.datasource.PolicyLiveDataSource
import com.solvathon.domain.repo.PolicyRepository
import com.solvathon.domain.service.PolicyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePolicyRepository(
        policyService: PolicyService
    ): PolicyRepository {
        return PolicyLiveDataSource (policyService)
    }
}

