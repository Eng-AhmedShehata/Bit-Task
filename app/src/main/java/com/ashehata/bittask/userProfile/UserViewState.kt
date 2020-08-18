package com.ashehata.saveme.home

import com.ashehata.bittask.models.getPhotos.DataPhotos
import com.ashehata.bittask.models.getProfile.Data
import com.ashehata.bittask.userProfile.ErrorType


data class UserViewState(
    var userData: Data? = null,
    var photosList: List<DataPhotos>? = null,
    var isLoading: Boolean = false,
    var error: ErrorType = ErrorType.NoError
)
