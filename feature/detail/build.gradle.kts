plugins {
    alias(libs.plugins.circuitSampleKmpFeature)
}

kotlin {
    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.feature.detail"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.circuitSharedElements)
                implementation(libs.coilCompose)
            }
        }
    }
}