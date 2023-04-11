package pt.andre.widgetcandy.configuration

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

internal data class CandyOption(
    val key: Preferences.Key<*>,
    val default: Any,
)

internal const val TEXT_KEY = "text"
internal const val TYPEFACE_KEY = "typeface"
internal const val IS_BOLD_KEY = "bold"
internal const val IS_ITALIAN_KEY = "italian"
internal const val IS_UNDERLINE_KEY = "underline"

private fun stringPreferences() = listOf(
    CandyOption(
        key = stringPreferencesKey(TEXT_KEY),
        default = "",
    ),
    CandyOption(
        key = stringPreferencesKey(TYPEFACE_KEY),
        default = "",
    ),
)

private fun booleanPreferences() = listOf(
    CandyOption(
        key = booleanPreferencesKey(IS_BOLD_KEY),
        default = false,
    ),
    CandyOption(
        key = booleanPreferencesKey(IS_ITALIAN_KEY),
        default = false,
    ),
    CandyOption(
        key = booleanPreferencesKey(IS_UNDERLINE_KEY),
        default = false,
    ),
)

internal fun preferences(): List<CandyOption> = stringPreferences() + booleanPreferences()
