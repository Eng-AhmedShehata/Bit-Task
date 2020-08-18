package com.ashehata.saveme.externals

import android.R.attr.label
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashehata.bittask.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar


fun View.showSnakeBar(message: String?) {
    if (message.isNullOrEmpty()) return
    Snackbar.make(
        this,
        message, Snackbar.LENGTH_SHORT
    ).show()
}


fun Activity.hideKeypad() {
    val imm: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    with(this.currentFocus) {
        imm.hideSoftInputFromWindow(this?.windowToken, 0)
    }
}

fun Context.showToast(message: String?) {
    if (message.isNullOrEmpty()) return
    Toast.makeText(
        this,
        message, Toast.LENGTH_SHORT
    ).show()
}

fun Boolean?.viewVisibility(): Int {
    if (this == null) return View.GONE
    return if (this) View.VISIBLE else View.GONE
}

fun RecyclerView.setUp(mAdapter: ListAdapter<Any, RecyclerView.ViewHolder>) {
    this.apply {
        adapter = mAdapter
        isNestedScrollingEnabled = false
        setHasFixedSize(true)
    }
}

fun ImageView.setTintColor(colorRes: Int) {
    this.setColorFilter(
        ContextCompat.getColor(context, colorRes),
        android.graphics.PorterDuff.Mode.SRC_IN
    );
}

fun RecyclerView.smoothScrollToEnd() {
    adapter?.itemCount?.let { this.smoothScrollToPosition(it) }
}

fun ImageView.load(url: String?) {
    if (url == null) return
    Glide.with(this).load(url).addListener(object : RequestListener<Drawable?> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable?>?,
            isFirstResource: Boolean
        ): Boolean {
            this@load.visibility = View.GONE
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable?>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            this@load.visibility = View.VISIBLE
            return false
        }
    }).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(this)
}

fun ImageView.load(url: String?, view: View) {
    if (url == null) return
    Glide.with(this).load(url).addListener(object : RequestListener<Drawable?> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable?>?,
            isFirstResource: Boolean
        ): Boolean {
            view.visibility = View.GONE
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable?>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            view.visibility = View.VISIBLE
            return false
        }
    }).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(this)
}


fun ImageView.load(path: Int) {
    Glide.with(this).load(path).into(this)
}
