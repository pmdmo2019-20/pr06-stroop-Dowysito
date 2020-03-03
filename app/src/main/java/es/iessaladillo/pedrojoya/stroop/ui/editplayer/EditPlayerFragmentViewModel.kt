package es.iessaladillo.pedrojoya.stroop.ui.addplayer

import android.app.Application
import android.text.Editable
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.data.Player
import es.iessaladillo.pedrojoya.stroop.data.Repository


class EditPlayerFragmentViewModel(
    private val repository: Repository,
    private val application: Application
) : ViewModel() {



    val avatares: List<Int> = avatars


    fun edit(player: Player) {
        Thread{
            repository.updatePlayer(player)
        }.start()
    }

    fun delete(player: Player): Boolean {
        Thread{
            repository.deletePlayer(player)
        }.start()
        return true
    }

}