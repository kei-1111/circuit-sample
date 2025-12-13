plugins {
    alias(libs.plugins.circuitSampleKmpLibrary)
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