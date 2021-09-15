package net.simplifiedcoding.multiviewlist.ui

sealed class HomeRecyclerViewItem {

    class Title(
        val id: Int,
        val title: String
    ) : HomeRecyclerViewItem()

    class News(
        val id: Int,
        val image: String,
        val title: String,
        val date: String,
        val news_count :Int
    ) : HomeRecyclerViewItem()

    class UpcomingBets(
        val id: Int,
        val compatitor: String,
        val letter: String,
        val name: String,
        val upcoming_count: Int
    ) : HomeRecyclerViewItem()

    class PastBets(
        val id: Int,
        val compatitors: String,
        val price: String,
        val range: String,
        val past_count: Int
    ) : HomeRecyclerViewItem()

}