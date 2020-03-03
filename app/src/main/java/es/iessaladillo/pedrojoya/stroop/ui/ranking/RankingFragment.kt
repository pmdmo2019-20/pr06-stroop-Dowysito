package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.Game
import es.iessaladillo.pedrojoya.stroop.data.GameDatabase
import es.iessaladillo.pedrojoya.stroop.data.LocalRepository
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.player_fragment.*
import kotlinx.android.synthetic.main.ranking_fragment.*

class RankingFragment:Fragment(R.layout.ranking_fragment) {

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(navHostFragment)
    }

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val listAdapter: RankingFragmentAdapter = RankingFragmentAdapter().also {
        it.setOnItemClickListener {position ->
            val game: Game =it.getItem(position)
            activityViewModel.player=viewModel.getUser(game)
            activityViewModel.game=game
            navController.navigate(R.id.action_summary_to_ranking)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> AlertDialog.Builder(requireContext()).setMessage(R.string.ranking_help_description).setTitle(R.string.help_title).setPositiveButton(R.string.help_accept){ _, _ -> }
                .create().show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private val viewModel: RankingFragmentViewModel by viewModels {
        RankingFragmentViewModelFactory(
            LocalRepository(GameDatabase.getInstance(requireContext()).dao),
            requireActivity().application
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        observe()
        setHasOptionsMenu(true)
        setupViews()
        setupAppBar()
    }

    private fun observe() {
        viewModel.allgames.observe(this){
            viewModel.allgamesshow=it
            if (spinner_ranking_modes.selectedItemPosition==0){
                listAdapter.submitList(viewModel.allgamesshow)
            }
        }
        viewModel.timegames.observe(this){
            viewModel.timegamesshow=it
            if (spinner_ranking_modes.selectedItemPosition==1){
                listAdapter.submitList(viewModel.timegamesshow)
            }
        }
        viewModel.attgames.observe(this){
            viewModel.attgamesshow=it
            if (spinner_ranking_modes.selectedItemPosition==2){
                listAdapter.submitList(viewModel.attgamesshow)
            }
        }
        viewModel.users.observe(this){
            viewModel.usersshow=it
            listAdapter.submitPlayerList(viewModel.usersshow)
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


    private fun setupViews() {
        spinner_ranking_modes.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> listAdapter.submitList(viewModel.allgamesshow)
                    1 -> listAdapter.submitList(viewModel.timegamesshow)
                    2 -> listAdapter.submitList(viewModel.attgamesshow)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { // sometimes you need nothing here
            }
        }

        if (settings.getString(getText(R.string.prefGameMode_key).toString(),"Time").equals("Time").toString().equals("Time")){
            spinner_ranking_modes.setSelection(1)
        }
        else{
            spinner_ranking_modes.setSelection(2)
        }
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.ranking_title)
        }
    }

    private fun setupRecyclerView(){
        list_games.run{
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }

    }

}