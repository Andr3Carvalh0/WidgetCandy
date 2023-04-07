plugins {
    id("com.android.application") version Versions.Build.GRADLE_TOOLS apply false
    id("com.android.library") version Versions.Build.GRADLE_TOOLS apply false
    id("org.jetbrains.kotlin.android") version Versions.Build.KOTLIN_GRADLE apply false
    id("org.jetbrains.kotlin.jvm") version Versions.Build.KOTLIN_JVM apply false
    id("io.gitlab.arturbosch.detekt") version Versions.Detekt.DETEKT
    id("com.google.dagger.hilt.android") version Versions.HILT apply false
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> { jvmTarget = Versions.Build.JVM_TARGET }

detekt {
    source = files(getAllSrcDirs())
    config = files("${rootProject.projectDir}/detekt_configuration.yml")
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins(Libraries.Detekt.Formatting)
}

fun getAllSrcDirs(): List<File> {
    val sourceDirs = mutableListOf<File>()
    subprojects.forEach {
        sourceDirs += file("${it.projectDir}/src/main/kotlin")
        sourceDirs += file("${it.projectDir}/src/test/kotlin")
        sourceDirs += file("${it.projectDir}/src/androidTest/kotlin")
    }
    return sourceDirs.filter { it.exists() }
}
