package es.iessaladillo.pedrojoya.stroop.ui.addplayer

import android.app.Application
import android.text.Editable
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.data.Player
import es.iessaladillo.pedrojoya.stroop.data.Repository


class AddPlayerFragmentViewModel(
    private val repository: Repository,
    private val application: Application
) : ViewModel() {

    var avatar: Int = R.drawable.avatar_01_mexican
    val avatares: List<Int> = avatars



    fun save(text: String) {
       Thread{
           repository.addPlayer(Player(0,text,avatar))
       }.start()
    }

}