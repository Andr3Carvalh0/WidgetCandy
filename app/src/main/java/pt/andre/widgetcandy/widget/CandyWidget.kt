package pt.andre.widgetcandy.widget

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text

internal class CandyWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Text(text = "Hello World!")
    }

    override val stateDefinition = PreferencesGlanceStateDefinition
}
