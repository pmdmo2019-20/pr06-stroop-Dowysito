package es.iessaladillo.pedrojoya.stroop.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.assistant_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
