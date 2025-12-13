plugins {
    alias(libs.plugins.circuitSampleKmpLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.model"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
        }
    }
}