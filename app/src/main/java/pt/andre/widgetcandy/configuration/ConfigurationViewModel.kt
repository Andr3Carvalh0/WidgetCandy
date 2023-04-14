package pt.andre.widgetcandy.configuration

import android.appwidget.AppWidgetManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.andre.widgetcandy.configuration.model.ViewState
import pt.andre.widgetcandy.configuration.storage.WidgetConfigurationStorage
import pt.andre.widgetcandy.configuration.usecase.GetUserWallpaperUseCase
import pt.andre.widgetcandy.utilities.IODispatcher
import pt.andre.widgetcandy.utilities.MainDispatcher

internal class ConfigurationViewModel @AssistedInject constructor(
    @Assisted private val widgetId: Int,
    private val storage: WidgetConfigurationStorage,
    private val getUserWallpaper: GetUserWallpaperUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val state: State<ViewState>
        get() = _state

    private val _state: MutableState<ViewState> =
        mutableStateOf(ViewState.Loading)

    init {
        if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            _state.value = ViewState.Failure
        } else {
            observePreferences()
        }
    }

    fun onItemClick(key: String, value: Any) {
        viewModelScope.launch(ioDispatcher) {
            when (value) {
                is Boolean -> {
                    storage.update(
                        widgetId = widgetId,
                        key = booleanPreferencesKey(key),
                        value = value,
                    )
                }
            }
        }
    }

    private fun observePreferences() {
        viewModelScope.launch(ioDispatcher) {
            storage.observe(widgetId)
                .collect { options ->
                    withContext(mainDispatcher) {
                        _state.value = ViewState.Content(
                            widgetId = widgetId,
                            text = options[stringPreferencesKey(TEXT_KEY)] as String,
                            textColor = Color(
                                options[longPreferencesKey(COLOR_KEY)] as Long,
                            ),
                            textSize = options[intPreferencesKey(SIZE_KEY)] as Int,
                            isBold = options[booleanPreferencesKey(IS_BOLD_KEY)] as Boolean,
                            isItalic = options[booleanPreferencesKey(IS_ITALIC_KEY)] as Boolean,
                            isUnderline = options[booleanPreferencesKey(IS_UNDERLINE_KEY)] as Boolean,
                            wallpaper = getUserWallpaper(),
                        )
                    }
                }
        }
    }
}
