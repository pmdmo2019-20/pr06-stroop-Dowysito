package es.iessaladillo.pedrojoya.stroop.ui.assistant

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AssistantAdapter (parentFragment: Fragment) :
    FragmentStateAdapter(parentFragment) {



    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> Assistant0Fragment.newInstance()
            1 -> Assistant1Fragment.newInstance()
            2 -> Assistant2Fragment.newInstance()
            3 -> Assistant3Fragment.newInstance()
            4 -> Assistant4Fragment.newInstance()
            5 -> Assistant5Fragment.newInstance()
            6 -> Assistant6Fragment.newInstance()
            7 -> Assistant7Fragment.newInstance()
            else -> throw IndexOutOfBoundsException("Invalid fragment index")
        }

    override fun getItemCount(): Int = 8
}