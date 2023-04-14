package pt.andre.widgetcandy.configuration.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.andre.widgetcandy.R
import pt.andre.widgetcandy.ui.CandyTheme
import pt.andre.widgetcandy.ui.SegmentedButton
import pt.andre.widgetcandy.ui.SegmentedButtonItem

private const val MAX_WIDTH_PERCENTAGE = 0.65f

@Composable
internal fun SegmentedOption(
    modifier: Modifier = Modifier.height(64.dp),
    title: String,
    options: List<SegmentedButtonItem>,
    onItemClick: (SegmentedButtonItem, Boolean) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = title)

        Column(
            modifier = Modifier.fillMaxWidth(MAX_WIDTH_PERCENTAGE),
            verticalArrangement = Arrangement.Center,
        ) {
            SegmentedButton(
                modifier = Modifier,
                items = options,
                onItemClick = { id ->
                    val item = options.findLast { it.id == id } ?: return@SegmentedButton
                    onItemClick(
                        item,
                        !item.isChecked,
                    )
                },
            )
        }
    }
}

@Preview
@Composable
private fun SegmentedOptionPreview() {
    CandyTheme {
        SegmentedOption(
            title = "Text style",
            options = listOf(
                SegmentedButtonItem(
                    id = "0",
                    isChecked = false,
                    drawable = R.drawable.ic_format_bold,
                ),
                SegmentedButtonItem(
                    id = "0",
                    isChecked = false,
                    drawable = R.drawable.ic_format_italic,
                ),
                SegmentedButtonItem(
                    id = "0",
                    isChecked = false,
                    drawable = R.drawable.ic_format_underline,
                ),
            ),
            onItemClick = { _, _ -> },
        )
    }
}
