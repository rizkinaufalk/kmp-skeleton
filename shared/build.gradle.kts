import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kmpNativeCoroutines)
    id("app.cash.sqldelight") version "2.1.0"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.decompose.ext.android)
            implementation(libs.decompose.ext.compose)
            implementation(libs.sqldelight.android)
//            implementation(libs.decompose.jetbrains)
        }

        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.ktor.content)
            implementation(libs.ktor.auth)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.logging)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)
            implementation(libs.logging.napier)
            implementation(libs.decompose)
            implementation(libs.decompose.ext.compose)
            implementation(libs.sqldelight.coroutines)
//            implementation(libs.sqldelight.runtime)
//            implementation(libs.decompose.coroutine)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native)
        }

        jvmMain.dependencies {
            implementation(libs.sqldelight.jvm)
        }
    }

    sqldelight {
        databases {
            create("RickAppDB") {
                packageName.set("org.example.kmpskeleton")
                verifyMigrations = false
            }
        }
    }
}

android {
    namespace = "org.example.kmpskeleton.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
