package max.keils.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bin_details"
)
data class BinDetailsEntity(
    @PrimaryKey val bin: String,
    val scheme: String,
    val type: String,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded(prefix = "country_") val country: CountryInfoEntity,
    @Embedded(prefix = "bank_") val bank: BankInfoEntity
)