package my.aolika.dependencyinjection


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import my.aolika.api.RetrofitApi
import my.aolika.roomdb.ArtDB
import my.aolika.utill.Util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent ::class)
object AppModule {
    @Singleton
    @Provides
    fun injectRoomDB(@ApplicationContext context: Context)
    = Room.databaseBuilder(context,
    ArtDB::class.java, "ArtBookDB").build()

    @Singleton
    @Provides
    fun injectDao(database: ArtDB) = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }

}