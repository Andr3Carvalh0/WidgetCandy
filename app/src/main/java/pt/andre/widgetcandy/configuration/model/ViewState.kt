package pt.andre.widgetcandy.configuration.model

sealed class ViewState {

    data class Content(
        val widgetId: Int,
    ) : ViewState()
    object Failure : ViewState()
    object Loading : ViewState()
}
