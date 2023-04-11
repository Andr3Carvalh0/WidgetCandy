package pt.andre.widgetcandy.configuration

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

internal data class WidgetOptions(
    val key: Preferences.Key<*>,
    val default: Any,
)

internal const val TEXT_KEY = "text"
internal const val TYPEFACE_KEY = "typeface"
internal const val IS_BOLD_KEY = "bold"
internal const val IS_ITALIAN_KEY = "italian"
internal const val IS_UNDERLINE_KEY = "underline"

internal fun preferences(): List<WidgetOptions> = stringPreferences() + booleanPreferences()

private fun stringPreferences() = listOf(
    WidgetOptions(
        key = stringPreferencesKey(TEXT_KEY),
        default = "",
    ),
    WidgetOptions(
        key = stringPreferencesKey(TYPEFACE_KEY),
        default = "",
    ),
)

private fun booleanPreferences() = listOf(
    WidgetOptions(
        key = booleanPreferencesKey(IS_BOLD_KEY),
        default = false,
    ),
    WidgetOptions(
        key = booleanPreferencesKey(IS_ITALIAN_KEY),
        default = false,
    ),
    WidgetOptions(
        key = booleanPreferencesKey(IS_UNDERLINE_KEY),
        default = false,
    ),
)
