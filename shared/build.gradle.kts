import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.aboutLibraries)
    alias(libs.plugins.circuitSampleKmpLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.metro)
}

ksp {
    arg("circuit.codegen.mode", "metro")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-P", "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=io.github.kei_1111.circuit.sample.core.common.CommonParcelize")
    }

    androidLibrary {
        namespace = "io.github.kei_1111.circuit.sample.shared"
    }

    sourceSets {
        commonMain {
            kotlin {
                srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            }
            dependencies {
                implementation(projects.feature.main)
                implementation(projects.feature.oss)
                implementation(projects.feature.settings)
                implementation(projects.core.common)
                implementation(projects.core.navigation)
                implementation(projects.core.model)
                implementation(projects.core.domain)
                implementation(projects.core.data)
                implementation(projects.core.local)
                implementation(projects.core.designsystem)

                implementation(libs.androidxDataStorePreferences)
                implementation(libs.circuitCodegenAnnotations)
                implementation(libs.circuitFoundation)
                implementation(libs.circuitSharedElements)
                implementation(libs.coilCompose)
                implementation(libs.coilNetworkKtor3)
                implementation(libs.composeMaterial3)
                implementation(libs.composeRuntime)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.ktorClientOkhttp)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.ktorClientDarwin)
            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.circuitCodegen)
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (this is AbstractKotlinCompile<*>) {
        incremental = false
    }
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
