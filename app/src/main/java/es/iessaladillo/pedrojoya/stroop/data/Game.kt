package es.iessaladillo.pedrojoya.stroop.data

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "game"
)
data class Game (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "minutes")
    var minutes: Int,
    @ColumnInfo(name = "words")
    var words: Int,
    @ColumnInfo(name = "correct")
    var correct: Int,
    @ColumnInfo(name = "points")
    var points: Int,
    @ColumnInfo(name = "incorrect")
    var incorrect: Int,
    @ColumnInfo(name = "idplayer")
    var idplayer: Long
)