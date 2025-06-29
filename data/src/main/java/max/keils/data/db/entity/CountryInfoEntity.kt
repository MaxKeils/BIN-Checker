package max.keils.data.db.entity

data class CountryInfoEntity(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)