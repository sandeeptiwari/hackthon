package com.solvathon.di

import android.content.Context
import com.solvathon.domain.datasource.AuthenticationLiveDataSource
import com.solvathon.domain.datasource.PolicyLiveDataSource
import com.solvathon.domain.repo.PolicyRepository
import com.solvathon.domain.repo.UserRepository
import com.solvathon.domain.service.AuthenticationService
import com.solvathon.domain.service.PolicyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideUserRepository(
        authenticationService: AuthenticationService
    ): UserRepository {
        return AuthenticationLiveDataSource (authenticationService)
    }
}

