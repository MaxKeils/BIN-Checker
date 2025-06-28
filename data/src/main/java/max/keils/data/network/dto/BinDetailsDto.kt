package max.keils.data.network.dto

import com.google.gson.annotations.SerializedName

data class BinDetailsDto(
    @SerializedName("scheme")
    val scheme: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("prepaid")
    val prepaid: Boolean?,
    @SerializedName("country")
    val country: CountryInfoDto?,
    @SerializedName("bank")
    val bank: BankInfoDto?
)