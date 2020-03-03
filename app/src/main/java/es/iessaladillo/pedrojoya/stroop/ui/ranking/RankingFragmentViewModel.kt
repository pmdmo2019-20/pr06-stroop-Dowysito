package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.data.Game
import es.iessaladillo.pedrojoya.stroop.data.Player
import es.iessaladillo.pedrojoya.stroop.data.Repository

class RankingFragmentViewModel(
    private val repository: Repository,
    private val application: Application
) : ViewModel() {

    val users: LiveData<List<Player>> = repository.queryAllPlayers()
    val allgames: LiveData<List<Game>> =repository.queryAllGames()
    val timegames: LiveData<List<Game>> =repository.queryAllGamesTime()
    val attgames: LiveData<List<Game>> =repository.queryAllGamesAttempts()

    var allgamesshow:List<Game> = emptyList()
    var timegamesshow:List<Game> = emptyList()
    var attgamesshow:List<Game> = emptyList()
    var usersshow: List<Player> = emptyList()



    fun getUser(game: Game): Player? {
        var user = users.value!!.find { x -> x.id == game.idplayer }
        return user
    }





}