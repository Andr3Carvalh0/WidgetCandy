object Libraries {

    object AndroidX {
        const val Core = "androidx.core:core-ktx:${Versions.AndroidX.CORE}"
        const val Datastore = "androidx.datastore:datastore-preferences:${Versions.AndroidX.DATASTORE}"
        const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LIFECYCLE}"
        const val Glance = "androidx.glance:glance-appwidget:${Versions.AndroidX.GLANCE}"
    }

    object Compose {
        const val Activity = "androidx.activity:activity-compose:${Versions.AndroidX.ACTIVITY}"
        const val BOM = "androidx.compose:compose-bom:${Versions.Compose.BOM}"
        const val Google_Fonts = "androidx.compose.ui:ui-text-google-fonts:${Versions.Compose.GOOGLE_FONTS}"
        const val UI = "androidx.compose.ui:ui"
        const val Material3 = "androidx.compose.material3:material3"
        const val Graphics = "androidx.compose.ui:ui-graphics"
        const val Preview = "androidx.compose.ui:ui-tooling-preview"
        const val Tooling = "androidx.compose.ui:ui-tooling"
        const val Lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.LIFECYCLE}"

        object Test {
            const val Manifest = "androidx.compose.ui:ui-test-manifest"
        }
    }

    object Detekt {
        const val Formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.Detekt.DETEKT}"
    }

    object Hilt {
        const val Core = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val Compiler = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val Navigation = "androidx.hilt:hilt-navigation-compose:${Versions.Compose.HILT}"
    }

    const val JUnit = "junit:junit:${Versions.JUnit.MAIN}"

    object Kotlin {
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINES}"

        object Test {
            const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.COROUTINES}"
        }
    }

    const val Mockk = "io.mockk:mockk:${Versions.MOCKK}"

    const val Turbine = "app.cash.turbine:turbine:${Versions.TURBINE}"
}
