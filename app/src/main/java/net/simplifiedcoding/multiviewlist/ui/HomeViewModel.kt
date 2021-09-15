package net.simplifiedcoding.multiviewlist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.simplifiedcoding.multiviewlist.data.network.Resource
import net.simplifiedcoding.multiviewlist.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<Resource<List<HomeRecyclerViewItem>>>()
    val homeListItemsLiveData: LiveData<Resource<List<HomeRecyclerViewItem>>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

    private fun getHomeListItems() = viewModelScope.launch {
        _homeListItemsLiveData.postValue(Resource.Loading)
        val newsDeferred = async { repository.getNews() }
        val upcomingDeferred = async { repository.getUpcomingbets() }
        val pastBetsDeferred = async { repository.getPastbets() }

        val news = newsDeferred.await()
        val upcoming = upcomingDeferred.await()
        val pastBets = pastBetsDeferred.await()

        val homeItemsList = mutableListOf<HomeRecyclerViewItem>()
        if(news is Resource.Success && upcoming is Resource.Success  && pastBets is Resource.Success){
            homeItemsList.add(HomeRecyclerViewItem.Title(1, "Recommended Movies"))
            homeItemsList.addAll(news.value)
            homeItemsList.add(HomeRecyclerViewItem.Title(2, "Upcoming Bets"))
            homeItemsList.addAll(upcoming.value)
            homeItemsList.add(HomeRecyclerViewItem.Title(3, "Past Bets"))
            homeItemsList.addAll(pastBets.value)
            _homeListItemsLiveData.postValue(Resource.Success(homeItemsList))
            Log.i("MYTAG", "success")
        }else{
            Resource.Failure(false, null, null)
            Log.i("MYTAG", "failure")
        }
    }
}