plugins {
    kotlin("kapt")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = Configuration.NAMESPACE
    compileSdk = Configuration.COMPILE_SDK

    defaultConfig {
        applicationId = Configuration.NAMESPACE
        minSdk = Configuration.MINIMUM_SDK
        targetSdk = Configuration.TARGET_SDK
        versionCode = Configuration.VERSION_CODE
        versionName = Configuration.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".debug"
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.BUILD
    }

    packaging {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    compileOptions {
        sourceCompatibility = Versions.Build.JAVA_VERSION
        targetCompatibility = Versions.Build.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = Versions.Build.JVM_TARGET
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(Libraries.Compose.Activity)
    implementation(Libraries.Accompanist.Permission)
    implementation(Libraries.AndroidX.Core)
    implementation(Libraries.AndroidX.Datastore)
    implementation(Libraries.AndroidX.Lifecycle)
    implementation(Libraries.AndroidX.Glance)
    implementation(platform(Libraries.Compose.BOM))
    implementation(Libraries.Compose.Google_Fonts)
    implementation(Libraries.Compose.UI)
    implementation(Libraries.Compose.Graphics)
    implementation(Libraries.Compose.Preview)
    implementation(Libraries.Compose.Material3)
    implementation(Libraries.Compose.Lifecycle)
    implementation(Libraries.Hilt.Core)
    implementation(Libraries.Hilt.Navigation)
    implementation(Libraries.MaterialComponents)

    kapt(Libraries.Hilt.Compiler)

    debugImplementation(Libraries.Compose.Tooling)
    debugImplementation(Libraries.Compose.Test.Manifest)

    testImplementation(Libraries.JUnit)
    testImplementation(Libraries.Kotlin.Test.Coroutines)
    testImplementation(Libraries.Mockk)
    testImplementation(Libraries.Turbine)
}
