plugins {
    alias(libs.plugins.circuitSampleKmpFeature)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.feature.oss"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.aboutLibrariesCore)
            implementation(libs.aboutLibrariesComposeM3)
        }
    }
}