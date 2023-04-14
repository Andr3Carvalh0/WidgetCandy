package pt.andre.widgetcandy.configuration.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import pt.andre.widgetcandy.R
import pt.andre.widgetcandy.configuration.ConfigurationViewModel
import pt.andre.widgetcandy.configuration.IS_BOLD_KEY
import pt.andre.widgetcandy.configuration.IS_ITALIC_KEY
import pt.andre.widgetcandy.configuration.IS_UNDERLINE_KEY
import pt.andre.widgetcandy.configuration.model.ViewState
import pt.andre.widgetcandy.ui.SegmentedButtonItem

@Composable
internal fun ConfigurationScreen(
    vm: ConfigurationViewModel,
    finish: (code: Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (val state = vm.state.value) {
            is ViewState.Content -> PreviewConfiguration(
                metadata = state,
                onItemClick = vm::onItemClick,
            )
            ViewState.Failure -> finish(ComponentActivity.RESULT_CANCELED)
            ViewState.Loading -> Text(text = "Loading...")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PreviewConfiguration(
    metadata: ViewState.Content,
    onItemClick: (key: String, value: Any) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        PreviewSurface(
            modifier = Modifier.fillMaxSize()
                .padding(
                    start = padding.calculateStartPadding(LayoutDirection.Ltr),
                    top = 0f.dp,
                    bottom = 0f.dp,
                    end = padding.calculateEndPadding(LayoutDirection.Ltr),
                ),
            padding = padding,
            text = metadata.text,
            textColor = metadata.textColor,
            textSize = TextUnit(metadata.textSize.toFloat(), TextUnitType.Sp),
            isItalic = metadata.isItalic,
            isUnderline = metadata.isUnderline,
            isBold = metadata.isBold,
            wallpaper = metadata.wallpaper,
        ) {
            Column(
                modifier = Modifier.padding(top = 8.dp),
            ) {
                SegmentedOption(
                    title = "Text style",
                    options = listOf(
                        SegmentedButtonItem(
                            id = IS_BOLD_KEY,
                            isChecked = metadata.isBold,
                            drawable = R.drawable.ic_format_bold,
                        ),
                        SegmentedButtonItem(
                            id = IS_ITALIC_KEY,
                            isChecked = metadata.isItalic,
                            drawable = R.drawable.ic_format_italic,
                        ),
                        SegmentedButtonItem(
                            id = IS_UNDERLINE_KEY,
                            isChecked = metadata.isUnderline,
                            drawable = R.drawable.ic_format_underline,
                        ),
                    ),
                    onItemClick = { item, isChecked ->
                        onItemClick(
                            item.id,
                            isChecked,
                        )
                    },
                )
            }
        }
    }
}
