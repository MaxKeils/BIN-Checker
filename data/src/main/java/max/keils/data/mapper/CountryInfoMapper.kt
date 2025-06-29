package max.keils.data.mapper

import max.keils.data.db.entity.CountryInfoEntity
import max.keils.data.network.dto.CountryInfoDto
import max.keils.domain.entity.CountryInfo
import javax.inject.Inject

class CountryInfoMapper @Inject constructor() {

    fun mapDtoToDomainModel(countryInfoDto: CountryInfoDto): CountryInfo = CountryInfo(
        numeric = countryInfoDto.numeric ?: UNKNOWN,
        alpha2 = countryInfoDto.alpha2 ?: UNKNOWN,
        name = countryInfoDto.name ?: UNKNOWN,
        emoji = countryInfoDto.emoji ?: UNKNOWN,
        currency = countryInfoDto.currency,
        latitude = countryInfoDto.latitude,
        longitude = countryInfoDto.longitude
    )

    fun mapDbEntityToDomainModel(countryInfoEntity: CountryInfoEntity): CountryInfo = CountryInfo(
        numeric = countryInfoEntity.numeric,
        alpha2 = countryInfoEntity.alpha2,
        name = countryInfoEntity.name,
        emoji = countryInfoEntity.emoji,
        currency = countryInfoEntity.currency,
        latitude = countryInfoEntity.latitude,
        longitude = countryInfoEntity.longitude
    )

    fun mapDtoToDbEntity(countryInfoDto: CountryInfoDto): CountryInfoEntity = CountryInfoEntity(
        numeric = countryInfoDto.numeric ?: UNKNOWN,
        alpha2 = countryInfoDto.alpha2 ?: UNKNOWN,
        name = countryInfoDto.name ?: UNKNOWN,
        emoji = countryInfoDto.emoji ?: UNKNOWN,
        currency = countryInfoDto.currency,
        latitude = countryInfoDto.latitude,
        longitude = countryInfoDto.longitude
    )



    companion object {
        private const val UNKNOWN = "Unknown"
    }

}