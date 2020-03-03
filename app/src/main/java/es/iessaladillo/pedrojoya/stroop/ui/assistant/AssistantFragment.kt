package es.iessaladillo.pedrojoya.stroop.ui.assistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.assistant_fragment.*


class AssistantFragment: Fragment(R.layout.assistant_fragment) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAppBar()
        setupViewPager()
        setupTabLayoutMediator()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.assistant_title)
        }
    }

    private fun setupTabLayoutMediator() {
        TabLayoutMediator(tabLayout, viewPager) {_ , _ ->
        }.attach()
    }

    private fun setupViewPager() {
        viewPager.adapter = AssistantAdapter(this)
    }


}