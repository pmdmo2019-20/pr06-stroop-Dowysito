package es.iessaladillo.pedrojoya.stroop.ui.addplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.player_fragment_item.view.*

class EditPlayerFragmentAdapter: RecyclerView.Adapter<EditPlayerFragmentAdapter.ViewHolder>() {

    private var data: List<Int> = emptyList()

    private var onItemClickListener: ((Int) -> Unit)? = null


    fun setOnItemClickListener(listener: ((Int) -> Unit)?){
        onItemClickListener= listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.add_player_fragment_item, parent, false)
        return ViewHolder(itemView,onItemClickListener)
    }

    fun getItem(position: Int):Int{
        return data[position]
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun submitList(avatars: List<Int>) {
        data=avatars
        notifyDataSetChanged()
    }


    inner class ViewHolder(override val containerView: View, onItemClickListener: ((Int) -> Unit)?) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


        init {
            containerView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }


        fun bind(resId: Int) {
            resId.run {
                containerView.imgUser.run {
                    this.setImageDrawable(resources.getDrawable(resId))
                }
            }
        }
    }


}