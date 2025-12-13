plugins {
    alias(libs.plugins.circuitSampleKmpLibrary)
    alias(libs.plugins.kotlinParcelize)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-P",
            "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=io.github.kei_1111.circuit.sample.core.common.CommonParcelize"
        )
    }

    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.core.navigation"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(libs.circuitFoundation)
        }
    }
}