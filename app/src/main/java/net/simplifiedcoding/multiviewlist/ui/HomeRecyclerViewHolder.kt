package net.simplifiedcoding.multiviewlist.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import net.simplifiedcoding.multiviewlist.R
import net.simplifiedcoding.multiviewlist.databinding.*

sealed class HomeRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: HomeRecyclerViewItem, position: Int) -> Unit)? = null

    class TitleViewHolder(private val binding: ItemTitleBinding) : HomeRecyclerViewHolder(binding){
        fun bind(title: HomeRecyclerViewItem.Title){
            binding.textViewTitle.text = title.title
            binding.textViewAll.setOnClickListener {
                itemClickListener?.invoke(it, title, adapterPosition)
            }
        }
    }

    class NewsViewHolder(private val binding: ItemNewsBinding) : HomeRecyclerViewHolder(binding){
        fun bind(news: HomeRecyclerViewItem.News){
            binding.imageViewNews.loadImage(news.image)
            binding.textViewContent.text = news.title
            binding.textViewDate.text = news.date
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it,news,adapterPosition)
            }
        }
    }

    class UpcomingbetsViewHolder(private val binding: ItemUpcomingBetsBinding) : HomeRecyclerViewHolder(binding){
        fun bind(upcomingBets: HomeRecyclerViewItem.UpcomingBets){
            binding.bettingPerson.text = upcomingBets.compatitor
            binding.firstLetter.text = upcomingBets.letter
            binding.fullName.text = upcomingBets.name
        }
    }
    class PastbetsViewHolder(private val binding: ItemPastBetsBinding) : HomeRecyclerViewHolder(binding){
        fun bind(pastBets: HomeRecyclerViewItem.PastBets){
            binding.textTitle.text = pastBets.compatitors
            binding.textPrice.text = pastBets.price
            binding.textRange.text = pastBets.range
        }
    }
}