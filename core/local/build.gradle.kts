plugins {
    alias(libs.plugins.circuitSampleKmpLibrary)
    alias(libs.plugins.metro)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.local"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.androidxDataStorePreferences)
        }
    }
}