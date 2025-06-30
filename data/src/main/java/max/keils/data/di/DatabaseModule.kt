package max.keils.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import max.keils.data.db.AppDataBase
import max.keils.data.db.dao.BinDetailsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(
            context = context,
            klass = AppDataBase::class.java,
            name = "bin_checker_database"
        ).build()

    @Provides
    @Singleton
    fun provideBinDetailsDao(appDataBase: AppDataBase): BinDetailsDao = appDataBase.binDetailsDao()

}