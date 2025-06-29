package max.keils.data.repository

import android.util.Log
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
    val mapper: BinDetailsMapper
) : BinRepository {
    override suspend fun getBinDetails(bin: String): Result<BinDetails> {
        return try {
            val response = api.getBinDetails(bin)
            Result.success(mapper.mapDtoToEntity(response))
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> Result.failure(BinLookupException(BinLookupError.BinNotFound))
                else -> Result.failure(BinLookupException(BinLookupError.ApiError("HTTP error: ${e.code()} - ${e.message}")))
            }
        } catch (e: IOException) {
            Log.d("UI_CHECKER", "ERROR: ${e}")
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
}