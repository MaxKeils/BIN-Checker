package max.keils.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import max.keils.data.db.dao.BinDetailsDao
import max.keils.data.db.entity.BinDetailsEntity

@Database(
    entities = [BinDetailsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun binDetailsDao(): BinDetailsDao

}