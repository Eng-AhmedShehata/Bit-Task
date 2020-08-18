package com.ashehata.bittask.userProfile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ashehata.bittask.R
import com.ashehata.bittask.models.getPhotos.DataPhotos
import com.ashehata.saveme.base.BaseFragment
import com.ashehata.saveme.externals.load
import com.ashehata.saveme.externals.showToast
import com.ashehata.saveme.externals.viewVisibility
import com.ashehata.saveme.home.UserIntent
import com.ashehata.saveme.home.UserViewModel
import com.ashehata.saveme.home.UserViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : BaseFragment(), PhotosAdapter.OnPhotoClicked {

    override fun layoutRes(): Int = R.layout.fragment_user

    @Inject
    lateinit var photosAdapter: PhotosAdapter
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Send action/intent to VM to get data
        getDataAction()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRv()
        updateUi()
    }

    private fun updateUi() {
        lifecycleScope.launch {
            viewModel.stateChannel.collect {

                handleUi(it)
                handleLoading(it)
                handleError(it)
            }
        }
    }

    private fun setupRv() {
        rv_photos.adapter = photosAdapter
        photosAdapter.onPhotoClicked(this)
    }

    private fun handleLoading(it: UserViewState?) {
        pb_loading.visibility = it?.isLoading.viewVisibility()
    }

    private fun handleUi(it: UserViewState?) {
        // display user info
        val data = it?.userData
        if (data != null) {
            tv_name.text = data.full_name
            tv_location.text = data.location
            tv_bio.text = data.bio
            iv_picture.load(data.profile_picture)

            // display user counts
            val counts =  data.counts
            tv_posts_num.text = counts.posts.toString()
            tv_followers_num.text = counts.followers.toString()
            tv_following_num.text = counts.following.toString()
        }

        if (!it?.photosList.isNullOrEmpty()) {
            // Pass data to adapter
            photosAdapter.submitList(it?.photosList)
        }
    }

    private fun handleError(it: UserViewState?) {
        when (val error = it?.error) {
            ErrorType.NoInternet -> print("")
            ErrorType.NoError -> print("")
            is ErrorType.ErrorNotFound -> requireContext().showToast(error.message)
        }
    }

    private fun getDataAction() {
        lifecycleScope.launchWhenCreated {
            viewModel.intentChannel.offer(UserIntent.GetProfileInfo)
        }
    }

    override fun onClicked(dataPhotos: DataPhotos) {
        val action = UserFragmentDirections.actionProfileToShowPhoto(dataPhotos.image)
        findNavController().navigate(action)
    }

}