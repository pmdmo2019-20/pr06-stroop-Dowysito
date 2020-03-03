package es.iessaladillo.pedrojoya.stroop.ui.game

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.Game
import es.iessaladillo.pedrojoya.stroop.data.GameDatabase
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.game_fragment.*
import kotlinx.android.synthetic.main.main_activity.*

class GameFragment:Fragment(R.layout.game_fragment) {

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    private val viewModel: GameViewModel by viewModels {
        GameFragmentViewModelFactory(
            LocalRepository(GameDatabase.getInstance(requireContext()).dao),
            requireActivity().application
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupAppBar()
        observe()
        viewModel.startGameThread(settings.getString(getText(R.string.prefGameTime_key).toString(),"60000")!!.toInt(),settings.getString(getText(R.string.prefWordTime_key).toString(),"2000")!!.toInt())
    }

    private fun observe() {
        viewModel.progress.observe(this){
            game_progress.progress=it
            if (it<=0){
                activityViewModel.game= Game(0,viewModel.gamemode,viewModel.max, viewModel.words.value!!,viewModel.correct.value!!,viewModel.points.value!!,viewModel.incorrect,viewModel.player.id)
                activityViewModel.player= activityViewModel.selectedPlayer!!
                navController.navigate(R.id.action_summary_to_dashboard)
            }
        }
        viewModel.correct.observe(this){txtCorrectnumber.text=it.toString()}
        viewModel.words.observe(this){txtWordsnumber.text=it.toString()}
        viewModel.colordata.observe(this){
            lblWord_Game.setTextColor(it)
        }
        viewModel.txt.observe(this){lblWord_Game.text=it}
        if (settings.getString(getText(R.string.prefGameMode_key).toString(),"Time").equals("Time")){
            viewModel.points.observe(this){txtAttemptsnumber_points.text=it.toString()}
        }else{
            viewModel.attempts.observe(this){
                txtAttemptsnumber_points.text=it.toString()
                if (it<=0){
                    activityViewModel.game= Game(0,viewModel.gamemode,viewModel.max, viewModel.words.value!!,viewModel.correct.value!!,viewModel.points.value!!,viewModel.incorrect,viewModel.player.id)
                    activityViewModel.player= activityViewModel.selectedPlayer!!
                    navController.navigate(R.id.action_summary_to_dashboard)
                }
            }
        }
    }

    private fun setupViews() {
        viewModel.max= settings.getString(getText(R.string.prefGameTime_key).toString(),"60000")!!.toInt()
        viewModel.player = activityViewModel.selectedPlayer!!
        viewModel.attempts.value= settings.getString(getText(R.string.prefAttempts_key).toString(),"5")!!.toInt()
        viewModel.gamemode = settings.getString(getText(R.string.prefGameMode_key).toString(),"Time").toString()
        game_progress.max= viewModel.max
        if (settings.getString(getText(R.string.prefGameMode_key).toString(),"Time").equals("Time")){
            lblAttempts_points.text=getText(R.string.game_points)
        }else{
            lblAttempts_points.text=getText(R.string.game_attempts)
            txtAttemptsnumber_points.text=settings.getString(getText(R.string.prefAttempts_key).toString(),"5")
        }
        img_correct.setOnClickListener { viewModel.checkRight() }
        img_incorrect.setOnClickListener { viewModel.checkWrong() }
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            title = ""
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

}