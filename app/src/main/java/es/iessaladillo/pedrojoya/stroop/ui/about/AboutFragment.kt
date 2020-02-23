package es.iessaladillo.pedrojoya.stroop.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import es.iessaladillo.pedrojoya.stroop.R

class AboutFragment: Fragment(R.layout.about_fragment) {


    private val navController: NavController by lazy {
        NavHostFragment.findNavController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setupViews()
    }

//    private fun setupViews() {
//        btn_toAbout.setOnClickListener { navController.navigate() }
//    }

}