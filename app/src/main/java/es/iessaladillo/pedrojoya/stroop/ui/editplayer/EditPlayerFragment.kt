package es.iessaladillo.pedrojoya.stroop.ui.editplayer

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.*
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.GameDatabase
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.addplayer.EditPlayerFragmentAdapter
import es.iessaladillo.pedrojoya.stroop.ui.addplayer.EditPlayerFragmentViewModel
import es.iessaladillo.pedrojoya.stroop.ui.addplayer.EditPlayerFragmentViewModelFactory
import kotlinx.android.synthetic.main.add_user_fragment.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.player_fragment.list_users

class EditPlayerFragment: Fragment(R.layout.edit_player_fragment) {

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }



    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val listAdapter: EditPlayerFragmentAdapter = EditPlayerFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            val avatar:Int=it.getItem(position)
            imgPlayer.setImageDrawable(resources.getDrawable(avatar))
            activityViewModel.selectedPlayer!!.avatar=avatar
        }
    }
    private val viewModel: EditPlayerFragmentViewModel by viewModels {
        EditPlayerFragmentViewModelFactory(
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
        listAdapter.submitList(viewModel.avatares)
    }

    private fun setupViews() {
        imgPlayer.setImageDrawable(resources.getDrawable(activityViewModel.selectedPlayer!!.avatar))
        txtNameEditPlayer.setText(activityViewModel.selectedPlayer!!.name)
        btn_save.setOnClickListener {
            activityViewModel.selectedPlayer!!.name = txtNameEditPlayer.text.toString()
            viewModel.edit(activityViewModel.selectedPlayer!!)
            navController.navigateUp()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.edit_player_menu, menu)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> AlertDialog.Builder(requireContext()).setMessage(R.string.player_deletion_message).setTitle(R.string.player_deletion_title).setPositiveButton(R.string.player_deletion_yes){ _,_ ->
                viewModel.delete(activityViewModel.selectedPlayer!!)
                activityViewModel.selectedPlayer=null
                navController.navigateUp()
            }.setNegativeButton(R.string.player_deletion_no){_,_ ->}.create().show()
            R.id.help -> AlertDialog.Builder(requireContext()).setMessage(R.string.player_edition_help_description).setTitle(R.string.help_title).setPositiveButton(R.string.help_accept){ _,_ -> }
                .create().show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.player_creation_title)
        }
    }

    private fun setupRecyclerView(){
        list_users.run{
            setHasFixedSize(true)
            layoutManager= GridLayoutManager(context,3)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }

    }
}