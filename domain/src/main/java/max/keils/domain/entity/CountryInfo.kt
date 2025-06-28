package max.keils.domain.entity

data class CountryInfo(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)
