package com.ashehata.saveme.home


sealed class UserIntent {
    object GetProfileInfo : UserIntent()
    object GetPhotos : UserIntent()
}
