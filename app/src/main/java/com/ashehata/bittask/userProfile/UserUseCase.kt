package com.ashehata.saveme.home

import com.ashehata.bittask.models.getPhotos.GetPhotos
import com.ashehata.bittask.models.getProfile.UserProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getUserProfile(): Flow<UserProfile> {
        return userRepository.getUserProfile()
    }

    fun getPhotos(): Flow<GetPhotos> {
        return userRepository.getPhotos()
    }

}
