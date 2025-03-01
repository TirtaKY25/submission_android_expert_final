package com.application.submission_android_expert.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.application.submission_android_expert.core.BuildConfig
import com.application.submission_android_expert.core.data.source.RestaurantRepository
import com.application.submission_android_expert.core.data.source.local.LocalDataSource
import com.application.submission_android_expert.core.data.source.local.preferences.SettingsPreferences
import com.application.submission_android_expert.core.data.source.local.preferences.dataStore
import com.application.submission_android_expert.core.data.source.local.room.RestaurantDatabase
import com.application.submission_android_expert.core.data.source.remote.RemoteDataSource
import com.application.submission_android_expert.core.data.source.remote.network.ApiService
import com.application.submission_android_expert.core.domain.repository.IRestaurantRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



val dbModule = module {
    factory { get<RestaurantDatabase>().restaurantDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("restaurant".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            RestaurantDatabase::class.java, "restaurant.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

@Suppress("unused")
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dark_mode_settings")
val prefModule = module {
    single { androidContext().dataStore }
    single { SettingsPreferences(get()) }
}

val networkModule = module {
    single {
        val hostname = "restaurant-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/DTfK4/xJroJfN4ZVD29ITgA2f8OWSO6Hm7H9G/sMKdA=")
            .add(hostname, "sha256/K7rZOrXHknnsEhUH8nLL4MZkejquUuIvOIr6tCa0rbo=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    single<IRestaurantRepository> { RestaurantRepository(get(), get()) }
}