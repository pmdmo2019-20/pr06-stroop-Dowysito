package es.iessaladillo.pedrojoya.stroop.ui.addplayer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository

class EditPlayerFragmentViewModelFactory(val localRepository: LocalRepository, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditPlayerFragmentViewModel(localRepository, application) as T
    }
}
