package pt.andre.widgetcandy.configuration.model

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color

sealed class ViewState {

    data class Content(
        val widgetId: Int,
        val wallpaper: Bitmap?,
        val text: String,
        val textColor: Color,
        val textSize: Int,
        val isItalic: Boolean,
        val isBold: Boolean,
        val isUnderline: Boolean,
    ) : ViewState()
    object Failure : ViewState()
    object Loading : ViewState()
}
