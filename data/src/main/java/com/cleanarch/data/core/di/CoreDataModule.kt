package com.cleanarch.data.core.di

import android.app.Application
import com.cleanarch.data.core.db.DemoCleanArchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class CoreDataModule {

    @Provides
    fun provideCoroutineScopeIO() = Dispatchers.IO

    @Provides
    fun provideDataBase(app: Application): DemoCleanArchDatabase =
        DemoCleanArchDatabase.getDatabase(app.applicationContext)

    @Provides
    @Named("BooksEndPoint")
    fun provideBooksEndPoint() = "https://www.googleapis.com/"

}