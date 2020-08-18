package com.ashehata.bittask.userProfile

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ashehata.bittask.R
import com.ashehata.saveme.base.BaseFragment
import com.ashehata.saveme.externals.showToast
import com.ashehata.saveme.home.UserIntent
import com.ashehata.saveme.home.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UserFragment : BaseFragment() {

    override fun layoutRes(): Int = R.layout.fragment_user

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.intentChannel.offer(UserIntent.GetProfileInfo)
        }
    }


    override suspend fun updateUi() {
        viewModel.stateChannel.collectLatest {

            when (val error = it?.error) {
                ErrorType.NoInternet -> print("")
                ErrorType.NoError -> print("")
                is ErrorType.ErrorNotFound -> requireContext().showToast(error.message)
            }
            requireContext().showToast(it?.userData?.full_name)
        }
    }

}