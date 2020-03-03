package es.iessaladillo.pedrojoya.stroop.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class Player (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "avatar")
    var avatar: Int
)