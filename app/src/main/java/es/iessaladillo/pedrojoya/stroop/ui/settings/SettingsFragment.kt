package es.iessaladillo.pedrojoya.stroop.ui.settings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.stroop.R

class SettingsFragment:Fragment(R.layout.settings_fragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupAppBar()
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
            R.id.help -> AlertDialog.Builder(requireContext()).setMessage(R.string.settings_help_description).setTitle(R.string.help_title).setPositiveButton(R.string.help_accept){ _, _ -> }
                .create().show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.settings_title)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

}