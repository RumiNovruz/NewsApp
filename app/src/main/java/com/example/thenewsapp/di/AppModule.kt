package com.example.thenewsapp.di

import android.content.Context
import androidx.room.Room
import com.example.thenewsapp.R
import com.example.thenewsapp.data.network.api.NewsAPI
import com.example.thenewsapp.data.local.db.ArticleDAO
import com.example.thenewsapp.data.local.db.ArticleDatabase
import com.example.thenewsapp.common.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            context.getString(R.string.article_database)
        ).build()
    }
  @Provides
  @Singleton
    fun provideContext(@ApplicationContext context: Context):Context {
        return context
    }

    @Provides
    @Singleton
    fun provideArticleDao(articleDB: ArticleDatabase): ArticleDAO {
        return articleDB.getArticleDao()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .client(client(interceptor()))
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit:Retrofit): NewsAPI =retrofit.create(NewsAPI::class.java)

    @Provides
    @Singleton
    fun client(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(25,TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun interceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()


}
