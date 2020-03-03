package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.Game
import es.iessaladillo.pedrojoya.stroop.data.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.player_fragment_item.view.*
import kotlinx.android.synthetic.main.ranking_fragment_item.view.*

class RankingFragmentAdapter : RecyclerView.Adapter<RankingFragmentAdapter.ViewHolder>() {

    private var data: List<Game> = emptyList()
    private var dataplayer: List<Player> = emptyList()

    private var onItemClickListener: ((Int) -> Unit)? = null


    fun setOnItemClickListener(listener: ((Int) -> Unit)?) {
        onItemClickListener = listener
    }

    fun getItem(position: Int): Game {
        return data[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.ranking_fragment_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun submitList(games: List<Game>) {
        data = games
        notifyDataSetChanged()
    }

    fun submitPlayerList(players: List<Player>) {
        dataplayer = players
        notifyDataSetChanged()
    }


    inner class ViewHolder(
        override val containerView: View,
        onItemClickListener: ((Int) -> Unit)?
    ) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(game: Game) {
            game.run {
                val player = dataplayer.find { x -> x.id == game.idplayer }
                containerView.ranking_imgUser.run {
                    this.setImageDrawable(resources.getDrawable(player!!.avatar))
                }
                containerView.lbl_ranking_item_player_name.text=player!!.name
                containerView.lbl_ranking_item_gamemode.text="Game mode: "+game.type
                containerView.lbl_ranking_item_minutes.text="Minutes: "+game.minutes/1000/60
                containerView.lbl_ranking_item_words.text="Words: "+game.words
                containerView.lbl_ranking_item_correct.text="Correct: "+game.correct
                containerView.points_ranking.text=game.points.toString()
            }
        }
    }
}


