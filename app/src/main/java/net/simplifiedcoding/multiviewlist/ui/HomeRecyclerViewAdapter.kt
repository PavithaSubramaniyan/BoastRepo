package net.simplifiedcoding.multiviewlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.simplifiedcoding.multiviewlist.R
import net.simplifiedcoding.multiviewlist.databinding.ItemNewsBinding
import net.simplifiedcoding.multiviewlist.databinding.ItemPastBetsBinding
import net.simplifiedcoding.multiviewlist.databinding.ItemTitleBinding
import net.simplifiedcoding.multiviewlist.databinding.ItemUpcomingBetsBinding
import java.lang.IllegalArgumentException

class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    var items = listOf<HomeRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: ((view: View, item: HomeRecyclerViewItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when (viewType) {
            R.layout.item_title -> HomeRecyclerViewHolder.TitleViewHolder(
                ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_news -> HomeRecyclerViewHolder.NewsViewHolder(
                ItemNewsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_upcoming_bets -> HomeRecyclerViewHolder.UpcomingbetsViewHolder(
                ItemUpcomingBetsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_past_bets -> HomeRecyclerViewHolder.PastbetsViewHolder(
                ItemPastBetsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        when (holder) {
            is HomeRecyclerViewHolder.NewsViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.News)
            is HomeRecyclerViewHolder.UpcomingbetsViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.UpcomingBets)
            is HomeRecyclerViewHolder.PastbetsViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.PastBets)
            is HomeRecyclerViewHolder.TitleViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Title)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeRecyclerViewItem.News -> R.layout.item_news
            is HomeRecyclerViewItem.UpcomingBets -> R.layout.item_upcoming_bets
            is HomeRecyclerViewItem.PastBets -> R.layout.item_past_bets
            is HomeRecyclerViewItem.Title -> R.layout.item_title
        }
    }
}