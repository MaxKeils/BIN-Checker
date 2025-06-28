package max.keils.domain.entity

data class BinDetails(
    val scheme: String,
    val type: String,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryInfo,
    val bank: BankInfo
)