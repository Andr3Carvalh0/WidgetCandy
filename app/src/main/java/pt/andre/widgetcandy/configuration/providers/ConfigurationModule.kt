package pt.andre.widgetcandy.configuration.providers

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConfigurationModule {

    @Provides
    internal fun provideGlanceManager(@ApplicationContext context: Context) =
        GlanceAppWidgetManager(context)
}
