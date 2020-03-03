package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DatabaseDao {

    @Insert
    fun insertPlayer(player: Player): Long

    @Insert
    fun insertGame(game: Game): Long

    @Query("SELECT * FROM player WHERE id = :id")
    fun queryplayer(id: Long): Player

    @Query("SELECT * FROM game WHERE id = :id")
    fun querygame(id: Long): Game

    @Query("SELECT * FROM player")
    fun queryAllPlayers(): LiveData<List<Player>>

    @Query("SELECT * FROM game ORDER BY points desc")
    fun queryAllGames(): LiveData<List<Game>>

    @Query("SELECT * FROM game WHERE type = 'Time' ORDER BY points desc")
    fun queryAllGamesTime(): LiveData<List<Game>>

    @Query("SELECT * FROM game WHERE type = 'Attempts' ORDER BY points desc")
    fun queryAllGamesAttempts(): LiveData<List<Game>>

    @Update
    fun updatePlayer(player: Player): Int

    @Delete
    fun deletePlayer(player: Player): Int

}