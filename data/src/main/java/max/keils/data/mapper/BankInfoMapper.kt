package max.keils.data.mapper

import max.keils.data.db.entity.BankInfoEntity
import max.keils.data.network.dto.BankInfoDto
import max.keils.domain.entity.BankInfo
import javax.inject.Inject

class BankInfoMapper @Inject constructor() {

    fun mapDtoToDomainModel(bankInfoDto: BankInfoDto): BankInfo = BankInfo(
        name = bankInfoDto.name ?: UNKNOWN,
        url = bankInfoDto.url ?: UNKNOWN,
        phone = bankInfoDto.phone ?: UNKNOWN,
        city = bankInfoDto.city ?: UNKNOWN
    )

    fun mapDbModelToDomainModel(bankInfoEntity: BankInfoEntity): BankInfo = BankInfo(
        name = bankInfoEntity.name,
        url = bankInfoEntity.url,
        phone = bankInfoEntity.phone,
        city = bankInfoEntity.city
    )

    fun mapDtoToDbEntity(bankInfoDto: BankInfoDto): BankInfoEntity = BankInfoEntity(
        name = bankInfoDto.name ?: UNKNOWN,
        url = bankInfoDto.url ?: UNKNOWN,
        phone = bankInfoDto.phone ?: UNKNOWN,
        city = bankInfoDto.city ?: UNKNOWN
    )

    companion object {
        private const val UNKNOWN = "Unknown"
    }

}