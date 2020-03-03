package es.iessaladillo.pedrojoya.stroop.ui.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.extensions.LayoutContainer
import es.iessaladillo.pedrojoya.stroop.data.Player
import kotlinx.android.synthetic.main.player_fragment_item.view.*

class PlayerFragmentAdapter: RecyclerView.Adapter<PlayerFragmentAdapter.ViewHolder>() {

    private var data: List<Player> = emptyList()


    private var onItemClickListener: ((Int) -> Unit)? = null


    fun setOnItemClickListener(listener: ((Int) -> Unit)?){
        onItemClickListener= listener
    }

    fun getItem(position: Int):Player{
        return data[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.player_fragment_item, parent, false)
        return ViewHolder(itemView,onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun submitList(players: List<Player>) {
        data=players
        notifyDataSetChanged()
    }


    inner class ViewHolder(override val containerView: View, onItemClickListener: ((Int) -> Unit)?) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(player: Player) {
            player.run {
                containerView.txtNameEditPlayer.text= player.name
                containerView.imgUser.run {
                    this.setImageDrawable(resources.getDrawable(player.avatar))
                }
            }
        }
    }


}