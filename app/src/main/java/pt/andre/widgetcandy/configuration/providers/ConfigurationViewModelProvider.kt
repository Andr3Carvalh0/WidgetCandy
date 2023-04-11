package pt.andre.widgetcandy.configuration.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import pt.andre.widgetcandy.configuration.ConfigurationViewModel

@AssistedFactory
internal interface ConfigurationViewModelProvider {
    fun create(widgetId: Int): ConfigurationViewModel
}

internal fun factory(
    factory: ConfigurationViewModelProvider,
    widgetId: Int,
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return factory.create(widgetId) as T
    }
}
