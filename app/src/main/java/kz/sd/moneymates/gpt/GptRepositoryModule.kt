package kz.sd.moneymates.gpt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.sd.moneymates.network.gpt.GptApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GptRepositoryModule {
    @Singleton
    @Provides
    fun provideGptRepository(api: GptApi): GptRepositoryImpl {
        return GptRepositoryImpl(api)
    }
}