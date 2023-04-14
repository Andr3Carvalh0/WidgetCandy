package pt.andre.widgetcandy.configuration

import android.appwidget.AppWidgetManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import pt.andre.widgetcandy.configuration.providers.ConfigurationViewModelProvider
import pt.andre.widgetcandy.configuration.providers.factory
import pt.andre.widgetcandy.configuration.ui.ConfigurationScreen
import pt.andre.widgetcandy.ui.CandyTheme
import javax.inject.Inject

@AndroidEntryPoint
class ConfigurationActivity : ComponentActivity() {

    @Inject
    internal lateinit var factory: ConfigurationViewModelProvider

    private val vm: ConfigurationViewModel by viewModels {
        factory(
            factory = factory,
            widgetId = intent.extras
                ?.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID,
                ) ?: AppWidgetManager.INVALID_APPWIDGET_ID,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setResult(RESULT_OK)

        setContent {
            CandyTheme {
                ConfigurationScreen(
                    vm = vm,
                    finish = { code ->
                        setResult(code)
                        finish()
                    },
                )
            }
        }
    }
}
