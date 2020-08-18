package com.ashehata.bittask.showPhoto

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ashehata.bittask.R
import com.ashehata.saveme.base.BaseFragment
import com.ashehata.saveme.externals.load
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : BaseFragment() {

    override fun layoutRes(): Int = R.layout.fragment_photo


    private val args: PhotoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadPhoto()

        // close fragment
        ib_close.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadPhoto() {
        val photoUrl = args.photoUrl
        iv_photo.load(photoUrl)
    }

}