package pt.andre.widgetcandy.configuration.usecase

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class GetUserWallpaperUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    @SuppressLint("MissingPermission")
    @Suppress("SwallowedException")
    operator fun invoke(): Bitmap? = try {
        WallpaperManager.getInstance(context)
            .drawable
            ?.toBitmapOrNull()
    } catch (e: SecurityException) {
        null
    }
}
