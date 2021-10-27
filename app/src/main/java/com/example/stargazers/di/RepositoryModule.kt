package com.example.stargazers.di

import com.example.stargazers.database.CacheMapper
import com.example.stargazers.database.UserDao
import com.example.stargazers.network.NetworkMapper
import com.example.stargazers.network.UserRetrofit
import com.example.stargazers.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository (
        userDao: UserDao,
        retrofit: UserRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ) : MainRepository {
        return MainRepository(userDao, retrofit, cacheMapper, networkMapper)
    }
}