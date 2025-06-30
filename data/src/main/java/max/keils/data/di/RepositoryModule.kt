package max.keils.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import max.keils.data.repository.BinRepositoryImpl
import max.keils.domain.repository.BinRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindBinRepository(impl: BinRepositoryImpl): BinRepository

}