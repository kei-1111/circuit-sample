plugins {
    alias(libs.plugins.circuitSampleKmpLibraryCompose)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.ui"
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidxActivityCompose)
        }
    }
}