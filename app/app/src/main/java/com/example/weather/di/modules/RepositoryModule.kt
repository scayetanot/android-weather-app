package com.example.weather.di.modules

import com.example.weather.data.api.ApiDarkSky
import com.example.weather.data_source.LocalDataSourceImpl
import com.example.weather.data_source.RemoteDataSourceImpl
import com.example.weather.di.IoDispatcher
import com.example.weather.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        api: ApiDarkSky,
        localData: LocalDataSourceImpl
    ): AppRepositoryImpl {
        val userDataSource = RemoteDataSourceImpl(api, ioDispatcher)
        return AppRepositoryImpl(userDataSource, localData, ioDispatcher)
    }
}