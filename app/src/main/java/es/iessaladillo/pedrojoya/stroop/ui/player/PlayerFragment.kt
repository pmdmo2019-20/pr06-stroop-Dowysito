package es.iessaladillo.pedrojoya.stroop.ui.player

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.*
import androidx.transition.Visibility
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.GameDatabase
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository
import es.iessaladillo.pedrojoya.stroop.data.Player
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.android.synthetic.main.player_fragment.imgPlayer
import kotlinx.android.synthetic.main.player_fragment.list_users

class PlayerFragment:Fragment(R.layout.player_fragment) {

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val listAdapter: PlayerFragmentAdapter = PlayerFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            val player: Player =it.getItem(position)
            btn_edit.visibility= View.VISIBLE
            imgPlayer.setImageDrawable(resources.getDrawable(player.avatar))
            txtPlayerName.text=player.name
            activityViewModel.selectedPlayer=player
        }
    }

    private val viewModel: PlayerFragmentViewModel by viewModels {
        PlayerFragmentViewModelFactory(
            LocalRepository(GameDatabase.getInstance(requireContext()).dao),
            requireActivity().application
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setHasOptionsMenu(true)
        setupViews()
        setupAppBar()
        observe()
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.dashboard_menu, menu)

        for (i in 0 until menu.size()) {
            var menuItem = menu.getItem(i)

            val icon = menuItem.icon
            if (icon != null) {
                DrawableCompat.setTint(
                    icon,
                    ContextCompat.getColor(requireContext(), android.R.color.white)
                )
            }
        }

        return super.onCreateOptionsMenu(menu, inflater)
    }


    private fun setupViews() {
        floating_action_button.setOnClickListener { navController.navigate(R.id.addPlayerFragment) }
        btn_edit.setOnClickListener {navController.navigate(R.id.editPlayerFragment) }
        if (activityViewModel.selectedPlayer == null) {
            btn_edit.visibility= View.GONE
            txtPlayerName.text=getText(R.string.player_selection_no_player_selected)
            imgPlayer.setImageDrawable(resources.getDrawable(R.drawable.logo))
        }
        else{
            btn_edit.visibility= View.VISIBLE
            txtPlayerName.text= activityViewModel.selectedPlayer!!.name
            imgPlayer.setImageDrawable(resources.getDrawable(activityViewModel.selectedPlayer!!.avatar))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> AlertDialog.Builder(requireContext()).setMessage(R.string.player_selection_help_description).setTitle(R.string.help_title).setPositiveButton(R.string.help_accept){ _, _ -> }
                .create().show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.player_selection_title)
        }
    }

    private fun observe() {
        viewModel.users.observe(this) {
            listAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView(){
        list_users.run{
            setHasFixedSize(true)
            layoutManager= GridLayoutManager(context,2)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }

    }

}