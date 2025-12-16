plugins {
    alias(libs.plugins.circuitSampleKmpLibraryCompose)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.designsystem"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(libs.circuitSharedElements)
            implementation(libs.coilCompose)
            implementation(libs.coilNetworkKtor3)
            implementation(libs.materialKolor)
        }
    }
}