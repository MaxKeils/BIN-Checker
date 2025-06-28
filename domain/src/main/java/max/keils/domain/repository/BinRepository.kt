package max.keils.domain.repository

import max.keils.domain.entity.BinDetails

interface BinRepository {

    suspend fun getBinDetails(bin: String): Result<BinDetails>

}