import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.material)
            implementation(libs.androidx.compose.materialWindow)
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.ui.tooling)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.compose.ui.foundation)
            implementation(libs.navigation.compose)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)
            implementation(libs.decompose)
            implementation(libs.decompose.ext.android)
            implementation(libs.decompose.ext.compose)
            implementation(libs.coil.compose)
            implementation(libs.coil.network)
//            implementation(libs.decompose.jetbrains)
            implementation(libs.koin.androidx.compose)
            implementation(libs.logging.napier)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.decompose)
            implementation(libs.coil.compose)
            implementation(libs.logging.napier)
//            implementation(libs.decompose.jetbrains)
            implementation(projects.shared)
            implementation(libs.kotlinx.serialization.json)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "org.example.kmpskeleton"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.kmpskeleton"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "org.example.kmpskeleton.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.kmpskeleton"
            packageVersion = "1.0.0"
        }
    }
}
