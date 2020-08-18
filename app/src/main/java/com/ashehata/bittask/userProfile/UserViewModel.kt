package com.ashehata.saveme.home

import androidx.hilt.lifecycle.ViewModelInject
import com.ashehata.bittask.userProfile.ErrorType
import com.ashehata.saveme.base.BaseViewModel
import kotlinx.coroutines.flow.*

class UserViewModel @ViewModelInject constructor(private val useCase: UserUseCase) :
    BaseViewModel<UserIntent, UserViewState>(UserViewState(isLoading = true)) {

    init {
        handleIntents {
            when (it) {
                UserIntent.GetProfileInfo -> getProfile()
                UserIntent.GetPhotos -> getPhotos()
            }
        }
        //...
    }



    private fun getProfile() {
        launchViewModelScope {
            useCase.getUserProfile().catch {
                _stateChannel.value = getCurrentState().copy(
                    isLoading = false,
                    error = ErrorType.ErrorNotFound(it.localizedMessage)
                )
            }.collect {
                _stateChannel.value = getCurrentState().copy(
                    userData = it.data,
                    isLoading = false,
                    error = ErrorType.NoError
                )
            }
        }
        getPhotos()
        //...
    }

    private fun getPhotos() {
        launchViewModelScope {
            useCase.getPhotos().catch {
                _stateChannel.value = getCurrentState().copy(
                    isLoading = false,
                    error = ErrorType.ErrorNotFound(it.localizedMessage)
                )
            }.collect {
                if (it.status) {
                    _stateChannel.value = getCurrentState().copy(
                        photosList = it.data,
                        isLoading = false,
                        error = ErrorType.NoError
                    )
                } else {
                    _stateChannel.value = getCurrentState().copy(
                        photosList = null,
                        isLoading = false,
                        error = ErrorType.NoError
                    )
                }
            }
        }
    }

}