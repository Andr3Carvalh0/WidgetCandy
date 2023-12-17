plugins {
    id("com.android.application") version Versions.Build.GRADLE_TOOLS apply false
    id("com.android.library") version Versions.Build.GRADLE_TOOLS apply false
    id("org.jetbrains.kotlin.android") version Versions.Build.KOTLIN_GRADLE apply false
    id("org.jetbrains.kotlin.jvm") version Versions.Build.KOTLIN_JVM apply false
    id("io.gitlab.arturbosch.detekt") version Versions.Detekt.DETEKT
    id("com.google.dagger.hilt.android") version Versions.HILT apply false
}

detekt {
    source = files(getAllSrcDirs())
    config = files("${rootProject.projectDir}/detekt.yml")
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins(Libraries.Detekt.Formatting)
}

fun getAllSrcDirs(): List<File> {
    val sourceDirs = mutableListOf<File>()
    subprojects.forEach {
        sourceDirs += file("${it.projectDir}/src/main/java")
        sourceDirs += file("${it.projectDir}/src/test/java")
        sourceDirs += file("${it.projectDir}/src/androidTest/java")
    }
    return sourceDirs.filter { it.exists() }
}
