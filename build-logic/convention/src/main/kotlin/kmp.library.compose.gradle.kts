import io.github.kei_1111.circuit.sample.buildlogic.libs
import io.github.kei_1111.circuit.sample.buildlogic.library

plugins {
    id("kmp.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    androidLibrary {
        androidResources.enable = true
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.library("composeRuntime"))
            implementation(libs.library("composeFoundation"))
            implementation(libs.library("composeMaterial3"))
            implementation(libs.library("composeUi"))
            implementation(libs.library("composeUiToolingPreview"))
            implementation(libs.library("composeComponentsResources"))
        }

        androidMain.dependencies {
            implementation(libs.library("composeUiTooling"))
        }
    }
}