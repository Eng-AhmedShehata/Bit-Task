package com.ashehata.saveme.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ashehata.saveme.externals.hideKeypad


abstract class BaseFragment : Fragment() {

    abstract fun layoutRes(): Int
    private var mView: View? = null

    abstract suspend fun updateUi()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            mView = inflater.inflate(layoutRes(), container, false)
        }
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenCreated {
            updateUi()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.hideKeypad()
    }
}