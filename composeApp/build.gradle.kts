import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-P", "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=io.github.kei_1111.circuit.sample.core.common.CommonParcelize")
    }

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidxActivityCompose)
        }
        commonMain.dependencies {
            implementation(libs.androidxLifecycleViewModelCompose)
            implementation(libs.androidxLifecycleRuntimeCompose)
            implementation(libs.circuitFoundation)
            implementation(libs.composeComponentsResources)
            implementation(libs.composeFoundation)
            implementation(libs.composeMaterial3)
            implementation(libs.composeRuntime)
            implementation(libs.composeUi)
            implementation(libs.composeUiToolingPreview)
            implementation(libs.kotlinxSerializationCore)
            implementation(libs.materialKolor)
        }
    }
}

android {
    namespace = "io.github.kei_1111.circuit.sample"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.github.kei_1111.circuit.sample"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
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
}
