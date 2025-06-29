package max.keils.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import max.keils.data.db.dao.BinDetailsDao
import max.keils.data.mapper.BinDetailsMapper
import max.keils.data.network.Api
import max.keils.domain.entity.BinDetails
import max.keils.domain.error.BinLookupError
import max.keils.domain.error.BinLookupException
import max.keils.domain.repository.BinRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    val api: Api,
    val mapper: BinDetailsMapper,
    val binDetailsDao: BinDetailsDao
) : BinRepository {
    override suspend fun getBinDetails(bin: String): Result<BinDetails> {
        return try {
           binDetailsDao.getBinDetails(bin)?.let {
                return@let Result.success(mapper.mapDbEntityToDomainModel(it))
            }

            val response = api.getBinDetails(bin)

            binDetailsDao.insertBinDetails(mapper.mapDtoToDbEntity(response, bin))

            Result.success(mapper.mapDtoToDomainModel(response, bin))
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> Result.failure(BinLookupException(BinLookupError.BinNotFound))
                else -> Result.failure(BinLookupException(BinLookupError.ApiError("HTTP error: ${e.code()} - ${e.message}")))
            }
        } catch (_: IOException) {
            Result.failure(BinLookupException(BinLookupError.NetworkError))
        } catch (e: BinLookupException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(
                BinLookupException(
                    BinLookupError.UnknownError(
                        e.message ?: "An unexpected error occurred"
                    )
                )
            )
        }
    }

    override fun getAllBinDetails(): Flow<List<BinDetails>> =
        binDetailsDao.getAllBinDetails().map {
            mapper.mapListDbEntityToListDomainModel(it)
        }
}