package com.ashehata.saveme.externals

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.preference.Preference
import com.google.android.play.core.review.ReviewManagerFactory
import java.util.*


fun Activity.restartActivity(cls: Class<*>?) {
    with(this) {
        finish()
        overridePendingTransition(0, 0)
        startActivity(Intent(this, cls))
        overridePendingTransition(0, 0)
    }
}

fun Preference.hideIcon(show: Boolean = false) {
    if (!show) {
        this.isIconSpaceReserved = false
    }
}


fun Context.changeAppLang(lang: String?) {

    if (lang == null) return
    //Toast.makeText(context, lang, Toast.LENGTH_SHORT).show();
    with(this.resources) {
        // Change locale settings in the app.
        val conf = configuration
        conf.setLocale(Locale(lang)) // API 17+ only.
        //updateConfiguration(conf, dm)
        this@changeAppLang.createConfigurationContext(conf)
    }
}

fun Activity.shareApp() {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "https://play.google.com/store/apps/details?id=$packageName"
        )
        type = "text/plain"
    }
    startActivity(sendIntent)
}

fun Activity.rateApp() {
    try {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packageName")
            )
        }
        startActivity(sendIntent)
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }

}

fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null) return false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (connectivityManager != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        } else {
            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            } catch (e: Exception) {
            }
        }
    }
    return false
}


fun Activity.reviewAppDialog() {
    val manager = ReviewManagerFactory.create(this)

    (manager.requestReviewFlow()).addOnCompleteListener { request ->
        if (request.isSuccessful) {
            // We got the ReviewInfo object
            val reviewInfo = request.result
            manager.launchReviewFlow(this, reviewInfo)
        } else {
            rateApp()
        }
    }

}