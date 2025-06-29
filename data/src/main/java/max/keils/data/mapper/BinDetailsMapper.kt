package max.keils.data.mapper

import max.keils.data.network.dto.BinDetailsDto
import max.keils.domain.entity.BankInfo
import max.keils.domain.entity.BinDetails
import max.keils.domain.entity.CountryInfo
import max.keils.domain.error.BinLookupError
import max.keils.domain.error.BinLookupException
import javax.inject.Inject

class BinDetailsMapper @Inject constructor() {

    fun mapDtoToEntity(dto: BinDetailsDto): BinDetails {
        val scheme = dto.scheme
            ?: throw BinLookupException(BinLookupError.ApiError("Missing 'scheme' in API response"))
        val type = dto.type
            ?: throw BinLookupException(BinLookupError.ApiError("Missing 'type' in API response"))
        val countryDto = dto.country
            ?: throw BinLookupException(BinLookupError.ApiError("Missing 'country' in API response"))
        val bankDto = dto.bank
            ?: throw BinLookupException(BinLookupError.ApiError("Missing 'bank' in API response"))
        val bankName = bankDto.name
            ?: throw BinLookupException(BinLookupError.ApiError("Missing 'bank.name' in API response"))

        val countryInfo = CountryInfo(
            numeric = countryDto.numeric ?: "",
            alpha2 = countryDto.alpha2 ?: "",
            name = countryDto.name ?: "Unknown Country",
            emoji = countryDto.emoji ?: "❓",
            currency = countryDto.currency,
            latitude = countryDto.latitude,
            longitude = countryDto.longitude
        )

        val bankInfo = BankInfo(
            name = bankName,
            url = bankDto.url ?: "Unknown",
            phone = bankDto.phone ?: "Unknown",
            city = bankDto.city ?: "Unknown"
        )

        return BinDetails(
            scheme = scheme,
            type = type,
            brand = dto.brand,
            prepaid = dto.prepaid,
            country = countryInfo,
            bank = bankInfo
        )
    }
}