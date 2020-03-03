package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository

class RankingFragmentViewModelFactory(val localRepository: LocalRepository, val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RankingFragmentViewModel(localRepository, application) as T
    }
}