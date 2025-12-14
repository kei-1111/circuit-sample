plugins {
    alias(libs.plugins.circuitSampleKmpLibrary)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.model"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.composeUi)
        }
    }
}