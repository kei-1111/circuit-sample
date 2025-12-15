plugins {
    alias(libs.plugins.circuitSampleKmpFeature)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.feature.settings"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.colorPickerCompose)
        }
    }
}
