package max.keils.binchecker.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import max.keils.domain.repository.BinRepository
import max.keils.domain.usecase.GetAllDetailsUseCase
import max.keils.domain.usecase.GetBinDetailsUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGelAllBinDetailsUseCase(repository: BinRepository) = GetAllDetailsUseCase(repository)

    @Provides
    fun provideGetBinDetailsUseCase(repository: BinRepository) = GetBinDetailsUseCase(repository)

}