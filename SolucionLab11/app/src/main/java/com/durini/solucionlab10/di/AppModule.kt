package com.durini.solucionlab10.di

import android.content.Context
import androidx.room.Room
import com.durini.solucionlab10.data.local.LabDatabase
import com.durini.solucionlab10.data.local.dao.CharacterDao
import com.durini.solucionlab10.data.local.model.Character
import com.durini.solucionlab10.data.remote.api.RickMortyApi
import com.durini.solucionlab10.data.repository.CharacterRepository
import com.durini.solucionlab10.data.repository.CharacterRepositoryimpl
import com.durini.solucionlab10.data.util.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun ProvideLogginInterceptor(): HttpLoggingInterceptor{
        val loggin = HttpLoggingInterceptor()
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggin
    }

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient) : RickMortyApi{
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RickMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext contex: Context) : LabDatabase{
        return Room.databaseBuilder(
            contex,
            LabDatabase::class.java,
            "labDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: LabDatabase): CharacterDao{
        return database.characterDao()
    }

    @Provides
    @Singleton

    fun provideCharacterRepository(api: RickMortyApi, dao: CharacterDao) : CharacterRepository{
        return CharacterRepositoryimpl(
            api = api,
            characterDao = dao
        )
    }

}
