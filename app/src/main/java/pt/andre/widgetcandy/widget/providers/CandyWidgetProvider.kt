package pt.andre.widgetcandy.widget.providers

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import pt.andre.widgetcandy.widget.CandyWidget

class CandyWidgetProvider : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = CandyWidget()
}
