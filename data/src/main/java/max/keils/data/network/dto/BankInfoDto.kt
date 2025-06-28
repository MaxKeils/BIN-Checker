package max.keils.data.network.dto

import com.google.gson.annotations.SerializedName

data class BankInfoDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("city")
    val city: String?
)