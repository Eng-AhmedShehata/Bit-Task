package com.ashehata.bittask.showPhoto

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ashehata.bittask.R
import com.ashehata.saveme.base.BaseFragment
import com.ashehata.saveme.externals.showToast
import com.ashehata.saveme.home.UserIntent
import com.ashehata.saveme.home.UserViewModel
import kotlinx.coroutines.flow.collectLatest

class PhotoFragment : BaseFragment() {

    override fun layoutRes(): Int = R.layout.fragment_user

    override suspend fun updateUi() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}