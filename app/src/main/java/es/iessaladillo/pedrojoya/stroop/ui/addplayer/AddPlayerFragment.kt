package es.iessaladillo.pedrojoya.stroop.ui.addplayer

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.*
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.GameDatabase
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository
import kotlinx.android.synthetic.main.add_user_fragment.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.player_fragment.list_users

class AddPlayerFragment:Fragment(R.layout.add_user_fragment) {

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    private val listAdapter: AddPlayerFragmentAdapter = AddPlayerFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            val avatar:Int=it.getItem(position)
            println(avatar)
            imgPlayer.setImageDrawable(resources.getDrawable(avatar))
            viewModel.avatar=avatar
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> AlertDialog.Builder(requireContext()).setMessage(R.string.player_creation_help_description).setTitle(R.string.help_title).setPositiveButton(R.string.help_accept){ _, _ -> }
                .create().show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private val viewModel: AddPlayerFragmentViewModel by viewModels {
        AddPlayerFragmentViewModelFactory(
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
        btn_save.setOnClickListener {
            viewModel.save(txtNameEditPlayer.text.toString())
            navController.navigateUp()
        }
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