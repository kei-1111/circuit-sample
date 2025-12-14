plugins {
    alias(libs.plugins.circuitSampleKmpLibrary)
    alias(libs.plugins.metro)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.domain"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(projects.core.data)
            implementation(libs.composeUi)
        }
    }
}