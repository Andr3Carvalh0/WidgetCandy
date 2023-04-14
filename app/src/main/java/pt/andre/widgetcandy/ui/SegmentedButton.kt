package pt.andre.widgetcandy.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pt.andre.widgetcandy.R

@Composable
internal fun SegmentedButton(
    modifier: Modifier = Modifier,
    items: List<SegmentedButtonItem>,
    onItemClick: (id: String) -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val widthPerChild = maxWidth.value / items.size

        Row(
            modifier = modifier.fillMaxWidth(),
        ) {
            items.forEachIndexed { index, item ->
                val position = when (index) {
                    0 -> SegmentedChildPosition.START
                    items.size - 1 -> SegmentedChildPosition.END
                    else -> SegmentedChildPosition.MIDDLE
                }

                SegmentedChild(
                    modifier = Modifier.width(widthPerChild.dp),
                    metadata = item,
                    position = position,
                    onItemClick = onItemClick,
                )
            }
        }
    }
}

@Composable
private fun SegmentedChild(
    modifier: Modifier = Modifier,
    stroke: Dp = 1.dp,
    iconPadding: Dp = 8.dp,
    iconSize: Dp = 24.dp,
    position: SegmentedChildPosition,
    metadata: SegmentedButtonItem,
    onItemClick: (id: String) -> Unit,
) {
    val corner = when (position) {
        SegmentedChildPosition.START -> RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
        SegmentedChildPosition.MIDDLE -> RoundedCornerShape(0.dp)
        SegmentedChildPosition.END -> RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
    }

    val backgroundColor = if (metadata.isChecked) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.surface
    }

    val iconColor = if (metadata.isChecked) {
        MaterialTheme.colorScheme.onSecondary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Column(
        modifier = modifier
            .clip(corner)
            .border(BorderStroke(stroke, MaterialTheme.colorScheme.outline), corner)
            .background(backgroundColor)
            .clickable { onItemClick(metadata.id) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.padding(iconPadding).size(iconSize),
            painter = painterResource(id = metadata.drawable),
            tint = iconColor,
            contentDescription = metadata.contentDescription,
        )
    }
}

internal data class SegmentedButtonItem(
    val id: String,
    val isChecked: Boolean,
    val contentDescription: String? = null,
    @DrawableRes val drawable: Int,
)

private enum class SegmentedChildPosition {
    START,
    MIDDLE,
    END,
}

@Preview
@Composable
private fun SegmentedButtonPreview() {
    CandyTheme {
        SegmentedButton(
            modifier = Modifier,
            items = listOf(
                SegmentedButtonItem(
                    id = "0",
                    isChecked = false,
                    drawable = R.drawable.ic_format_bold,
                ),
                SegmentedButtonItem(
                    id = "1",
                    isChecked = true,
                    drawable = R.drawable.ic_format_italic,
                ),
                SegmentedButtonItem(
                    id = "2",
                    isChecked = false,
                    drawable = R.drawable.ic_format_underline,
                ),
            ),
            onItemClick = { },
        )
    }
}

@Preview
@Composable
private fun SegmentedChildPreview() {
    CandyTheme {
        Row {
            listOf(
                SegmentedChildPosition.START,
                SegmentedChildPosition.MIDDLE,
                SegmentedChildPosition.END,
            ).forEachIndexed { index, position ->
                SegmentedChild(
                    metadata = SegmentedButtonItem(
                        id = "$index",
                        isChecked = false,
                        drawable = R.drawable.ic_format_bold,
                    ),
                    onItemClick = { },
                    position = position,
                )
            }
        }
    }
}
