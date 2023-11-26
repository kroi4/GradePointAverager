package il.co.averager.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import il.co.averager.data.model.Item
import il.co.averager.data.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ItemRepository(application)

    val items: LiveData<List<Item>>? = repository.getItems()

    fun addItem(item: Item) {
        repository.addItem(item)
    }

    fun deleteItem(item: Item) {
        repository.deleteItem(item)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun sumAll(): Int?{
        return repository.sumAll()
    }
}