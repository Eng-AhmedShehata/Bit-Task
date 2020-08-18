package com.ashehata.saveme.externals

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
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