package max.keils.data.mapper

import max.keils.data.db.entity.BinDetailsEntity
import max.keils.data.network.dto.BinDetailsDto
import max.keils.domain.entity.BinDetails
import javax.inject.Inject

class BinDetailsMapper @Inject constructor(
    val countryMapper: CountryInfoMapper,
    val bankMapper: BankInfoMapper
) {

    fun mapDtoToDomainModel(binDetailsDto: BinDetailsDto, bin: String): BinDetails = BinDetails(
        bin = bin,
        scheme = binDetailsDto.scheme ?: UNKNOWN,
        type = binDetailsDto.type ?: UNKNOWN,
        brand = binDetailsDto.brand,
        prepaid = binDetailsDto.prepaid,
        country = countryMapper.mapDtoToDomainModel(binDetailsDto.country),
        bank = bankMapper.mapDtoToDomainModel(binDetailsDto.bank)
    )

    fun mapDtoToDbEntity(binDetailsDto: BinDetailsDto, bin: String): BinDetailsEntity =
        BinDetailsEntity(
            bin = bin,
            scheme = binDetailsDto.scheme ?: UNKNOWN,
            type = binDetailsDto.type ?: UNKNOWN,
            brand = binDetailsDto.brand,
            prepaid = binDetailsDto.prepaid,
            country = countryMapper.mapDtoToDbEntity(binDetailsDto.country),
            bank = bankMapper.mapDtoToDbEntity(binDetailsDto.bank)
        )

    fun mapDbEntityToDomainModel(binDetails: BinDetailsEntity): BinDetails =
        BinDetails(
            bin = binDetails.bin,
            scheme = binDetails.scheme,
            type = binDetails.type,
            brand = binDetails.brand,
            prepaid = binDetails.prepaid,
            country = countryMapper.mapDbEntityToDomainModel(binDetails.country),
            bank = bankMapper.mapDbModelToDomainModel(binDetails.bank),
        )

    fun mapListDbEntityToListDomainModel(binDetailsListEntity: List<BinDetailsEntity>): List<BinDetails> =
        binDetailsListEntity.map { mapDbEntityToDomainModel(it) }

    companion object {
        private const val UNKNOWN = "Unknown"
    }
}