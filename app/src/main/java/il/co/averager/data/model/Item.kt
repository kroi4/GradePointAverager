package il.co.averager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item(

    val subject: String,
    val grade: Int,
    val point: Double
) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}