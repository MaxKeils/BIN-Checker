package max.keils.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import max.keils.data.db.entity.BinDetailsEntity

@Dao
interface BinDetailsDao {

    @Insert(entity = BinDetailsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinDetails(binDetailsEntity: BinDetailsEntity)

    @Query(value = "SELECT * FROM bin_details")
    fun getAllBinDetails(): Flow<List<BinDetailsEntity>>

    @Query(value = "SELECT * FROM bin_details WHERE bin =:bin LIMIT 1")
    suspend fun getBinDetails(bin: String): BinDetailsEntity?

}