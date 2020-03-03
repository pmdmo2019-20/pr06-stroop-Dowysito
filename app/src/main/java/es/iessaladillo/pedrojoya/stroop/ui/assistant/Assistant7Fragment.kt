package es.iessaladillo.pedrojoya.stroop.ui.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.assistant_7.*
import kotlinx.android.synthetic.main.main_activity.*

class Assistant7Fragment :Fragment(R.layout.assistant_7){

    companion object {
        fun newInstance() = Assistant7Fragment()
    }

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        btn_Finish.setOnClickListener { navController.navigateUp() }
    }

}