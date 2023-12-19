import org.gradle.api.JavaVersion

object Versions {

    object AndroidX {
        const val ACTIVITY = "1.8.2"
        const val CORE = "1.12.0"
        const val DATASTORE = "1.0.0"
        const val LIFECYCLE = "2.6.2"
        const val GLANCE = "1.0.0"
    }

    object Accompanist {
        const val PERMISSION = "0.32.0"
    }

    object Build {
        val JAVA_VERSION = JavaVersion.VERSION_17
        const val JVM_TARGET = "17"

        const val GRADLE_TOOLS = "8.2.0"
        const val KOTLIN_GRADLE = "1.9.21"
        const val KOTLIN_JVM = "1.9.21"
    }

    const val COIL = "2.2.2"
    const val MATERIAL_COMPONENTS = "1.11.0"

    object Compose {
        const val HILT = "1.1.0"
        const val BOM = "2023.10.01"
        const val BUILD = "1.5.6"
        const val GOOGLE_FONTS = "1.5.4"
        const val LIFECYCLE = "2.6.2"
        const val NAVIGATION = "2.5.3"
    }

    const val HILT = "2.49"

    object Detekt {
        const val DETEKT = "1.19.0"
    }

    object JUnit {
        const val MAIN = "4.13.2"
    }

    object Kotlin {
        const val COROUTINES = "1.7.3"
    }

    const val MOCKK = "1.13.8"

    const val TURBINE = "1.0.0"
}
