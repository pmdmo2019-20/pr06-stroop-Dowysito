package es.iessaladillo.pedrojoya.stroop.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController


import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.main_fragment.*

class DashboardFragment: Fragment(R.layout.main_fragment) {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment,container,false)
    }



    private val navController: NavController by lazy {
        findNavController(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        setupAppBar()
//        setupViews()
    }


//    private fun setupAppBar() {
//        (requireActivity() as AppCompatActivity).main_toolbar?.run {
//            setLogo(R.drawable.logo)
//            title = getString(R.string.dashboard_title)
//        }
//    }
//    private fun setupViews() {
//        btn_toAbout.setOnClickListener { navController.navigate(R.id.aboutFragment) }
//
//    }

}