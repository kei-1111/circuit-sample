plugins {
    alias(libs.plugins.circuitSampleKmpLibrary)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.data"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(projects.core.local)
            implementation(libs.androidxDataStorePreferences)
        }
    }
}