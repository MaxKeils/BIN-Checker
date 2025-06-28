package max.keils.domain.usecase

import max.keils.domain.entity.BinDetails
import max.keils.domain.error.BinLookupError
import max.keils.domain.error.BinLookupException
import max.keils.domain.repository.BinRepository

class GetBinDetailsUseCase(private val repository: BinRepository) {

    suspend operator fun invoke(bin: String): Result<BinDetails> {
        if (bin.length < 6 || bin.length > 8)
            return Result.failure(exception = BinLookupException(BinLookupError.InvalidBinFormat))

        return repository.getBinDetails(bin)
    }
}
