package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData

class LocalRepository(private val dao: DatabaseDao) : Repository {
    override fun queryAllPlayers(): LiveData<List<Player>> = dao.queryAllPlayers()
    override fun addPlayer(player: Player) = dao.insertPlayer(player)
    override fun updatePlayer(player: Player): Int = dao.updatePlayer(player)
    override fun deletePlayer(player: Player): Int = dao.deletePlayer(player)
    override fun queryplayer(id: Long): Player = dao.queryplayer(id)
    override fun insertGame(game: Game): Long = dao.insertGame(game)
    override fun querygame(id: Long): Game = dao.querygame(id)
    override fun queryAllGames(): LiveData<List<Game>> = dao.queryAllGames()
    override fun queryAllGamesTime(): LiveData<List<Game>> = dao.queryAllGamesTime()
    override fun queryAllGamesAttempts(): LiveData<List<Game>> = dao.queryAllGamesAttempts()

}