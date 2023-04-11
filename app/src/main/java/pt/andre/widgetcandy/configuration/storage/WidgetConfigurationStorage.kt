package pt.andre.widgetcandy.configuration.storage

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import pt.andre.widgetcandy.configuration.preferences
import javax.inject.Inject

internal class WidgetConfigurationStorage @Inject constructor(
    @ApplicationContext private val context: Context,
    private val manager: GlanceAppWidgetManager,
) {

    private val items: MutableStateFlow<Map<Preferences.Key<*>, Any>> = MutableStateFlow(emptyMap())

    suspend fun observe(widgetId: Int) = items.also { refresh(widgetId) }

    suspend fun update(widgetId: Int, action: suspend (t: MutablePreferences) -> Unit) {
        updateValues(
            widgetId = widgetId,
            action = { preferences ->
                action(preferences)
                refresh(widgetId)
            },
        )
    }

    private suspend fun refresh(widgetId: Int) =
        updateValues(widgetId) { values ->
            items.value = preferences().associate { option ->
                option.key to values.asMap()
                    .getOrElse(key = option.key, defaultValue = { option.default })
            }
        }

    private suspend fun updateValues(
        widgetId: Int,
        action: suspend (t: MutablePreferences) -> Unit,
    ) {
        updateAppWidgetState(
            context = context,
            glanceId = manager.getGlanceIdBy(widgetId),
        ) { action(it) }
    }
}
