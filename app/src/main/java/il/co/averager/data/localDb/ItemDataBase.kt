package il.co.averager.data.localDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import il.co.averager.data.model.Item

@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class ItemDataBase : RoomDatabase() {

    abstract fun itemsDao(): ItemDao

    companion object {

        @Volatile
        private var instance: ItemDataBase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, ItemDataBase::class.java, "items_db")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
}