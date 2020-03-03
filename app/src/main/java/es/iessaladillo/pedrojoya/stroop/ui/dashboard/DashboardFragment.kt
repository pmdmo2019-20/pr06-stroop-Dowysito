package es.iessaladillo.pedrojoya.stroop.ui.dashboard


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.GameDatabase
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.imgPlayer
import kotlinx.android.synthetic.main.main_fragment.toolbar
import kotlinx.android.synthetic.main.main_fragment.txtPlayerName
import kotlinx.android.synthetic.main.player_fragment.*

class DashboardFragment: Fragment(R.layout.main_fragment) {

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    lateinit var sharedPreferences: SharedPreferences

    private val navController: NavController by lazy {
        findNavController(navHostFragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupAppBar()
        setupViews()
        sharedPreferences= requireContext().getSharedPreferences("app_pref", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("first","yes").equals("yes")){
            navController.navigate(R.id.assistantFragment)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("first", "no")
            editor.apply()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.dashboard_menu, menu)

        for (i in 0 until menu.size()) {
            var menuItem = menu.getItem(i)

            val icon = menuItem.icon
            if (icon != null) {
                DrawableCompat.setTint(icon, ContextCompat.getColor(requireContext(), android.R.color.white))
            }
        }

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> AlertDialog.Builder(requireContext()).setMessage(R.string.dashboard_help_description).setTitle(R.string.help_title).setPositiveButton(R.string.help_accept){ _,_ -> }
                .create().show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.dashboard_title)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private fun setupViews() {
        card_About.setOnClickListener { navController.navigate(R.id.aboutFragment) }
        card_Assistant.setOnClickListener { navController.navigate(R.id.assistantFragment) }
        card_settings.setOnClickListener { navController.navigate(R.id.settingsFragment) }
        card_player.setOnClickListener { navController.navigate(R.id.playerFragment) }
        card_play.setOnClickListener {
            if (activityViewModel.selectedPlayer==null){
                navController.navigate(R.id.playerFragment)
            }else{
                navController.navigate(R.id.gameFragment)
            }
        }
        if (activityViewModel.selectedPlayer == null) {
            txtPlayerName.text=getText(R.string.app_name)
            imgPlayer.setImageDrawable(resources.getDrawable(R.drawable.logo))
        }
        else{
            txtPlayerName.text= activityViewModel.selectedPlayer!!.name
            imgPlayer.setImageDrawable(resources.getDrawable(activityViewModel.selectedPlayer!!.avatar))
        }
        card_Ranking.setOnClickListener { navController.navigate(R.id.rankingFragment) }
    }

}