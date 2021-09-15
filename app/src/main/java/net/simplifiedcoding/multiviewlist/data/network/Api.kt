package net.simplifiedcoding.multiviewlist.data.network


import net.simplifiedcoding.multiviewlist.ui.HomeRecyclerViewItem
import retrofit2.http.GET

interface Api {

    @GET("news")
    suspend fun getNews(): List<HomeRecyclerViewItem.News>


    @GET("upcomingbet")
    suspend fun getUpcomingBets(): List<HomeRecyclerViewItem.UpcomingBets>

    @GET("pastbets")
    suspend fun getPastBets(): List<HomeRecyclerViewItem.PastBets>
}