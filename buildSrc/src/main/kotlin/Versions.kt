import org.gradle.api.JavaVersion

object Versions {

    object AndroidX {
        const val ACTIVITY = "1.7.0"
        const val CORE = "1.10.0"
        const val LIFECYCLE = "2.6.1"
        const val ROOM = "2.5.1"
        const val GLANCE = "1.0.0-alpha05"
    }

    object Build {
        val JAVA_VERSION = JavaVersion.VERSION_1_8
        const val JVM_TARGET = "1.8"

        const val GRADLE_TOOLS = "7.4.0"
        const val KOTLIN_GRADLE = "1.7.20"
        const val KOTLIN_JVM = "1.8.10"
    }

    const val COIL = "2.2.2"

    object Compose {
        const val HILT = "1.0.0"
        const val BOM = "2023.04.00"
        const val BUILD = "1.4.4"
        const val GOOGLE_FONTS = "1.4.0"
        const val LIFECYCLE = "2.6.1"
        const val NAVIGATION = "2.5.3"
    }

    const val HILT = "2.45"

    object Detekt {
        const val DETEKT = "1.19.0"
    }

    object JUnit {
        const val MAIN = "4.13.2"
    }

    object Kotlin {
        const val COROUTINES = "1.6.4"
    }

    const val MOCKK = "1.13.4"

    const val TURBINE = "0.12.3"
}
