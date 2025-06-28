package max.keils.binchecker.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import max.keils.domain.repository.BinRepository
import max.keils.domain.usecase.GetBinDetailsUseCase

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetBinDetailsUseCase(repository: BinRepository) = GetBinDetailsUseCase(repository)

}