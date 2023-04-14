package pt.andre.widgetcandy.configuration.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import pt.andre.widgetcandy.R
import pt.andre.widgetcandy.ui.CandyTheme
import kotlin.random.Random

@Composable
@OptIn(ExperimentalPermissionsApi::class)
internal fun PreviewSurface(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    textSize: TextUnit,
    isItalic: Boolean,
    isBold: Boolean,
    isUnderline: Boolean,
    wallpaper: Bitmap?,
    padding: PaddingValues? = null,
    content: @Composable () -> Unit,
) {
    val storagePermissionState = rememberPermissionState(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    LaunchedEffect(key1 = storagePermissionState) {
        if (!storagePermissionState.status.isGranted) {
            storagePermissionState.launchPermissionRequest()
        }
    }

    Box(modifier = modifier) {
        Image(
            modifier = modifier,
            painter = wallpaper?.let {
                BitmapPainter(it.asImageBitmap())
            } ?: painterResource(id = R.drawable.preview),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Column(modifier = modifier) {
            TextPreview(
                text = text,
                textColor = textColor,
                textSize = textSize,
                isItalic = isItalic,
                isBold = isBold,
                isUnderline = isUnderline,
                topPadding = (padding?.calculateTopPadding()?.plus(24.dp)) ?: 32.dp,
            )

            Box(
                modifier = modifier.fillMaxSize()
                    .shadow(elevation = 8.dp)
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                content()
            }
        }
    }
}

@Composable
private fun TextPreview(
    modifier: Modifier = Modifier,
    leftPadding: Dp = 16.dp,
    topPadding: Dp = 32.dp,
    rightPadding: Dp = 16.dp,
    bottomPadding: Dp = 32.dp,
    text: String,
    textColor: Color,
    textSize: TextUnit,
    isItalic: Boolean,
    isBold: Boolean,
    isUnderline: Boolean,
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(
                start = leftPadding,
                top = topPadding,
                end = rightPadding,
                bottom = bottomPadding,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier,
            text = text,
            fontSize = textSize,
            color = textColor,
            style = LocalTextStyle.current.copy(
                shadow = Shadow(
                    color = colorResource(id = R.color.dark),
                    offset = Offset(1f, 1f),
                    blurRadius = 6f,
                ),
            ),
            fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None,
        )
    }
}

@Preview
@Composable
private fun PreviewSurfacePreview() {
    CandyTheme {
        PreviewSurface(
            modifier = Modifier.fillMaxSize(),
            text = "This is awesome",
            textColor = Color.White,
            textSize = TextUnit(Random.nextFloat(), TextUnitType.Sp),
            isItalic = true,
            isBold = true,
            isUnderline = true,
            wallpaper = null,
            content = { },
        )
    }
}
