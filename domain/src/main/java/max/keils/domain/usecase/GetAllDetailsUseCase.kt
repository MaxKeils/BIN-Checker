package max.keils.domain.usecase

import kotlinx.coroutines.flow.Flow
import max.keils.domain.entity.BinDetails
import max.keils.domain.repository.BinRepository

class GetAllDetailsUseCase(private val repository: BinRepository) {

    operator fun invoke(): Flow<List<BinDetails>> = repository.getAllBinDetails()

}