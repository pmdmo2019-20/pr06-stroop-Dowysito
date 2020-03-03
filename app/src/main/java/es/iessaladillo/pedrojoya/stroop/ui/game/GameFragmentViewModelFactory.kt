package es.iessaladillo.pedrojoya.stroop.ui.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository

class GameFragmentViewModelFactory(val localRepository: LocalRepository, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameViewModel(localRepository, application) as T
    }
}
