package es.iessaladillo.pedrojoya.stroop.ui.addplayer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository

class AddPlayerFragmentViewModelFactory(val localRepository: LocalRepository, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddPlayerFragmentViewModel(localRepository, application) as T
    }
}
