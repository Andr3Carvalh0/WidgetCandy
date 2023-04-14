@file:Suppress("MagicNumber")

package pt.andre.widgetcandy.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun CandyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFFAAD5D0),
            secondary = Color(0xFFAAD5D0),
            surface = Color.White,
            onPrimary = Color.White,
            onSurface = Color.Black,
            onSecondary = Color.Black,
            outline = Color(0xFF666666),
        ),
    ) {
        content()
    }
}
