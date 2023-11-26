package il.co.averager.data.localDb

import androidx.lifecycle.LiveData
import androidx.room.*
import il.co.averager.data.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(item: Item)

    @Delete
    fun deleteItem(vararg items:Item)

    @Update
    fun updateItem(item:Item)

    @Query("DELETE FROM items")
    fun deleteAll()

    @Query("SELECT * FROM items")
    fun getItems() : LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE id Like :id")
    fun getItem(id:Int) : LiveData<List<Item>>

    @Query("SELECT SUM(grade) AS grade FROM items")
    fun sumAll():Int

}