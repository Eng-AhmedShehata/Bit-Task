package com.ashehata.news.dataSource

import com.ashehata.bittask.models.UserProfile
import retrofit2.http.GET

interface RemoteData {

    @GET("profile")
    suspend fun getUserProfile(): UserProfile

    /* @GET("home")
     suspend fun getSourcesList(): SourcesResponse*/
}