package pt.andre.widgetcandy.configuration.storage

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import pt.andre.widgetcandy.configuration.preferences
import pt.andre.widgetcandy.utilities.time
import javax.inject.Inject

internal class WidgetConfigurationStorage @Inject constructor(
    @ApplicationContext private val context: Context,
    private val manager: GlanceAppWidgetManager,
) {

    // Glance doesnt support updating values from the activity, so as an hack we have this 1
    // second timer that refreshes the values previously obtained
    suspend fun observe(widgetId: Int): Flow<Map<Preferences.Key<*>, Any>> = channelFlow {
        time().collect {
            updateValues(widgetId) { values ->
                send(
                    preferences().associate { option ->
                        option.key to values.asMap()
                            .getOrElse(key = option.key, defaultValue = { option.default })
                    },
                )
            }
        }
    }

    suspend inline fun <reified T> update(widgetId: Int, key: Preferences.Key<T>, value: T) {
        updateValues(
            widgetId = widgetId,
            action = { preferences -> preferences[key] = value },
        )
    }

    private suspend fun updateValues(
        widgetId: Int,
        action: suspend (t: MutablePreferences) -> Unit,
    ) {
        updateAppWidgetState(
            context = context,
            glanceId = manager.getGlanceIdBy(widgetId),
            definition = PreferencesGlanceStateDefinition,
        ) {
            it.toMutablePreferences()
                .apply { action(this) }
        }
    }
}
