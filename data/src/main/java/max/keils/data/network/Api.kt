package max.keils.data.network

import max.keils.data.network.dto.BinDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/{bin}")
    suspend fun getBinDetails(
        @Path("bin") bin: String
    ): BinDetailsDto

}