package il.co.averager.data.repository

import android.app.Application
import il.co.averager.data.localDb.ItemDao
import il.co.averager.data.localDb.ItemDataBase
import il.co.averager.data.model.Item

class ItemRepository (application: Application){

    private var itemDao: ItemDao?

    init {
        val db  = ItemDataBase.getDatabase(application.applicationContext)
        itemDao = db?.itemsDao()
    }

    fun getItems() = itemDao?.getItems()

    fun addItem(item: Item) {
        itemDao?.addItem(item)
    }

    fun deleteItem(item: Item) {
        itemDao?.deleteItem(item)
    }

    fun getItem(id:Int)  = itemDao?.getItem(id)

     fun deleteAll() {
        itemDao?.deleteAll()
    }

    fun sumAll(): Int? {
        return itemDao?.sumAll()
    }

}