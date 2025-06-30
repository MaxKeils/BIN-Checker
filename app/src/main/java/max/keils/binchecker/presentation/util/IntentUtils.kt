package max.keils.binchecker.presentation.util

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun openUrl(context: Context, url: String) {
    val uri = if (url.startsWith("http")) url else "https://$url"
    Intent(Intent.ACTION_VIEW, uri.toUri()).let {
        context.startActivity(it)
    }
}

fun openPhone(context: Context, phone: String) {
    Intent(Intent.ACTION_DIAL, "tel:$phone".toUri()).let {
        context.startActivity(it)
    }
}

fun openMap(context: Context, latitude: Double, longitude: Double) {
    val uri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
    Intent(Intent.ACTION_VIEW, uri).let {
        context.startActivity(it)
    }
}