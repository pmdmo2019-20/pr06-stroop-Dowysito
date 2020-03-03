package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData

interface Repository {

    fun queryAllPlayers(): LiveData<List<Player>>
    fun addPlayer(player: Player): Long
    fun updatePlayer(player: Player): Int
    fun deletePlayer(player: Player): Int
    fun queryplayer(id: Long): Player
    fun insertGame(game: Game): Long
    fun querygame(id: Long): Game
    fun queryAllGames(): LiveData<List<Game>>
    fun queryAllGamesTime(): LiveData<List<Game>>
    fun queryAllGamesAttempts(): LiveData<List<Game>>
}