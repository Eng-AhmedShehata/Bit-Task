package com.ashehata.news.dataSource

import com.ashehata.bittask.models.getPhotos.GetPhotos
import com.ashehata.bittask.models.getProfile.UserProfile
import retrofit2.http.GET

interface RemoteData {

    @GET("profile")
    suspend fun getUserProfile(): UserProfile

     @GET("home")
     suspend fun getPhotos(): GetPhotos
}