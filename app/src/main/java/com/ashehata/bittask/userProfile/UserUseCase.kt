package com.ashehata.saveme.home

import com.ashehata.bittask.models.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getUserProfile(): Flow<UserProfile> {
        return userRepository.getUserProfile()
    }

}
