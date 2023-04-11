package pt.andre.widgetcandy.configuration

import android.appwidget.AppWidgetManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.andre.widgetcandy.configuration.model.ViewState
import pt.andre.widgetcandy.configuration.storage.WidgetConfigurationStorage
import pt.andre.widgetcandy.utilities.IODispatcher
import pt.andre.widgetcandy.utilities.MainDispatcher

internal class ConfigurationViewModel @AssistedInject constructor(
    @Assisted private val widgetId: Int,
    private val storage: WidgetConfigurationStorage,
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

    private fun observePreferences() {
        viewModelScope.launch(ioDispatcher) {
            storage.observe(widgetId)
                .collect { options ->
                    withContext(mainDispatcher) {
                        _state.value = ViewState.Content(widgetId)
                    }
                }
        }
    }
}
