package es.iessaladillo.pedrojoya.stroop.ui.summary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.summary_fragment.*

class SummaryFragment: Fragment(R.layout.summary_fragment) {


    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    private val activityViewModel: MainActivityViewModel by activityViewModels()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupAppBar()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.game_result_title)
        }
    }

    private fun setupViews() {
        lblsumm_correct.text=activityViewModel.game!!.correct.toString()
        lblsumm_incorrect.text=activityViewModel.game!!.incorrect.toString()
        lblsumm_pointstext.text=activityViewModel.game!!.points.toString()

        summ_imgPlayer.setImageDrawable(resources.getDrawable(activityViewModel.player!!.avatar))
        summ_txtPlayerName.text=activityViewModel.player!!.name
    }

}