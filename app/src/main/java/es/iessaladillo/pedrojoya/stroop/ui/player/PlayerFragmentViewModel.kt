package es.iessaladillo.pedrojoya.stroop.ui.player

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.data.Player
import es.iessaladillo.pedrojoya.stroop.data.Repository


class PlayerFragmentViewModel(
    private val repository: Repository,
    private val application: Application
) : ViewModel() {


    val users: LiveData<List<Player>> = repository.queryAllPlayers()


}