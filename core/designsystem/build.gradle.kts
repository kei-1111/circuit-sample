plugins {
    alias(libs.plugins.circuitSampleKmpLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.designsystem"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(libs.composeFoundation)
            implementation(libs.composeMaterial3)
            implementation(libs.composeRuntime)
            implementation(libs.materialKolor)
        }
    }
}