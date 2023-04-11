package pt.andre.widgetcandy.configuration.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pt.andre.widgetcandy.configuration.ConfigurationViewModel
import pt.andre.widgetcandy.configuration.model.ViewState

@Composable
internal fun ConfigurationScreen(
    vm: ConfigurationViewModel,
    finish: (code: Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (val state = vm.state.value) {
            is ViewState.Content -> {
                Text(text = "Configuration screen for widgetId: ${state.widgetId}")
                Button(
                    onClick = { finish(ComponentActivity.RESULT_OK) },
                ) {
                    Text(text = "Add!")
                }
            }
            ViewState.Failure -> finish(ComponentActivity.RESULT_CANCELED)
            ViewState.Loading -> Text(text = "Loading...")
        }
    }
}
