package com.ashehata.saveme.home

import com.ashehata.bittask.models.getPhotos.GetPhotos
import com.ashehata.bittask.models.getProfile.UserProfile
import com.ashehata.news.dataSource.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject
constructor(
    private val remoteData: RemoteData
) {

    fun getUserProfile(): Flow<UserProfile> {
        return flow {
            emit(remoteData.getUserProfile())
        }
    }


    fun getPhotos(): Flow<GetPhotos> {
        return flow {
            emit(remoteData.getPhotos())
        }
    }


}
