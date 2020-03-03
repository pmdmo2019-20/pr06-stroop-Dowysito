package es.iessaladillo.pedrojoya.stroop.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.main_fragment.*

class AboutFragment: Fragment(R.layout.about_fragment) {


    private val navController: NavController by lazy {
        NavHostFragment.findNavController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAppBar()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            title = getString(R.string.dashboard_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

}