package es.iessaladillo.pedrojoya.stroop.ui

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.data.Game
import es.iessaladillo.pedrojoya.stroop.data.Player

class MainActivityViewModel: ViewModel() {


    var selectedPlayer :Player? = null
    var player: Player? = null
    var game: Game? = null

}