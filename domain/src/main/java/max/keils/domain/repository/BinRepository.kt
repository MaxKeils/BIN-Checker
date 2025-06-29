package max.keils.domain.repository

import kotlinx.coroutines.flow.Flow
import max.keils.domain.entity.BinDetails

interface BinRepository {

    suspend fun getBinDetails(bin: String): Result<BinDetails>

    fun getAllBinDetails(): Flow<List<BinDetails>>

}