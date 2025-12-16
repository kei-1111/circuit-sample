plugins {
    alias(libs.plugins.circuitSampleBuildKonfig)
    alias(libs.plugins.circuitSampleKmpFeature)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.feature.main"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.circuitSharedElements)
            }
        }
    }
}
