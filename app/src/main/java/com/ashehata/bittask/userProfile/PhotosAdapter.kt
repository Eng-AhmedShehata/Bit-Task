package com.ashehata.bittask.userProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashehata.bittask.R
import com.ashehata.bittask.models.getPhotos.DataPhotos
import com.ashehata.saveme.externals.DiffCallback
import com.ashehata.saveme.externals.load
import kotlinx.android.synthetic.main.root_photo.view.*
import javax.inject.Inject

class PhotosAdapter @Inject constructor() :
    ListAdapter<DataPhotos, PhotosAdapter.CategoryViewHolder>(DiffCallback<DataPhotos>()){

    private lateinit var onPhotoClicked: OnPhotoClicked

    interface OnPhotoClicked {
        fun onClicked(dataPhotos: DataPhotos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val dataPhotos = getItem(position)
        holder.bind(dataPhotos)
        holder.itemView.setOnClickListener {
            onPhotoClicked.onClicked(dataPhotos)
        }
    }


    fun onPhotoClicked(clicked: OnPhotoClicked) {
        this.onPhotoClicked = clicked
    }

    class CategoryViewHolder(
        inflater: LayoutInflater, parent: ViewGroup
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.root_photo, parent, false)) {

        private var photo: ImageView = itemView.iv_root_photo

        fun bind(dataPhotos: DataPhotos) {
            photo.load(dataPhotos.image)
        }
    }
}
