package com.cleanarch.data.book.di

import com.cleanarch.data.core.db.DemoCleanArchDatabase
import com.cleanarch.data.book.local.database.source.BooksLocalDataSource
import com.cleanarch.data.book.local.database.source.IBooksLocalDataSource
import com.cleanarch.data.book.remote.rest.api.BooksApi
import com.cleanarch.data.book.remote.rest.source.BooksRemoteDataSource
import com.cleanarch.data.book.remote.rest.source.IBooksRemoteDataSource
import com.cleanarch.data.book.repo.BooksRepository
import com.cleanarch.domain.volume.repo.IBooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class BookModule {

    @Provides
    fun provideBookAPI(builder: Retrofit.Builder,@Named("BooksEndPoint") endpointURL: String): BooksApi {
        return builder.baseUrl(endpointURL).build().create(BooksApi::class.java)
    }

    @Provides
    fun provideBookRemoteDataSource(remote: BooksRemoteDataSource): IBooksRemoteDataSource = remote

    @Provides
    fun provideBookLocalDataSource(impl: BooksLocalDataSource): IBooksLocalDataSource = impl

    @Provides
    fun provideBookDao(db: DemoCleanArchDatabase) = db.bookDao()

    @Provides
    fun provideBookRepo(impl: BooksRepository): IBooksRepository = impl

}