package es.iessaladillo.pedrojoya.stroop.ui.player

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository

class PlayerFragmentViewModelFactory(val localRepository: LocalRepository, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayerFragmentViewModel(localRepository, application) as T
    }
}
