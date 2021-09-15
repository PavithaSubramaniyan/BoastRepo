package net.simplifiedcoding.multiviewlist.data.repository

import net.simplifiedcoding.multiviewlist.data.network.Api
import net.simplifiedcoding.multiviewlist.data.network.SafeApiCall
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api
) : SafeApiCall {
    suspend fun getNews() = safeApiCall { api.getNews() }
    suspend fun getUpcomingbets() = safeApiCall { api.getUpcomingBets() }
    suspend fun getPastbets() = safeApiCall { api.getPastBets() }
}