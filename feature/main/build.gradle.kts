plugins {
    alias(libs.plugins.circuitSampleKmpFeature)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.feature.main"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.navigation)
            implementation(projects.core.model)
            implementation(projects.core.domain)
            implementation(projects.core.designsystem)
            implementation(libs.composeComponentsResources)
        }
    }
}
