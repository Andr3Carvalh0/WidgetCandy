package pt.andre.widgetcandy.configuration

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

internal data class WidgetOptions(
    val key: Preferences.Key<*>,
    val default: Any,
)

internal const val TEXT_KEY = "text"
internal const val SIZE_KEY = "size"
internal const val COLOR_KEY = "color"
internal const val TYPEFACE_KEY = "typeface"
internal const val IS_BOLD_KEY = "bold"
internal const val IS_ITALIC_KEY = "italic"
internal const val IS_UNDERLINE_KEY = "underline"

internal fun preferences(): List<WidgetOptions> = intPreferences() +
    longPreferences() +
    stringPreferences() +
    booleanPreferences()

private fun intPreferences() = listOf(
    WidgetOptions(
        key = intPreferencesKey(SIZE_KEY),
        default = 24,
    ),
)

private fun longPreferences() = listOf(
    WidgetOptions(
        key = longPreferencesKey(COLOR_KEY),
        default = 0xFFFFFFFF,
    ),
)

private fun stringPreferences() = listOf(
    WidgetOptions(
        key = stringPreferencesKey(TEXT_KEY),
        default = "CandyWidget",
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
        key = booleanPreferencesKey(IS_ITALIC_KEY),
        default = false,
    ),
    WidgetOptions(
        key = booleanPreferencesKey(IS_UNDERLINE_KEY),
        default = false,
    ),
)
