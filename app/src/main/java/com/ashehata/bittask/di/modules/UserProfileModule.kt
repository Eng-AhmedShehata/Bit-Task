package com.ashehata.saveme.di.modules

import com.ashehata.news.dataSource.RemoteData
import com.ashehata.saveme.home.UserRepository
import com.ashehata.saveme.home.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class UserProfileModule {

    @Provides
    fun provideProfileUseCase(repository: UserRepository) = UserUseCase(repository)

    @Provides
    fun provideProfileRepository(remoteData: RemoteData)
            = UserRepository(remoteData)

}