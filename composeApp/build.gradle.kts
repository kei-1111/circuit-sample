import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinMultiplatformLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.kotlinSerialization)
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
        androidResources.enable = true

        namespace = "io.github.kei_1111.circuit.sample.shared"
        compileSdk = libs.versions.androidCompileSdk.get().toInt()
        minSdk = libs.versions.androidMinSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            kotlin {
                srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            }

            dependencies {
                implementation(libs.androidxDataStorePreferences)
                implementation(libs.androidxLifecycleViewModelCompose)
                implementation(libs.androidxLifecycleRuntimeCompose)
                implementation(libs.circuitCodegenAnnotations)
                implementation(libs.circuitFoundation)
                implementation(libs.composeComponentsResources)
                implementation(libs.composeFoundation)
                implementation(libs.composeMaterial3)
                implementation(libs.composeRuntime)
                implementation(libs.composeUi)
                implementation(libs.composeUiToolingPreview)
                implementation(libs.kotlinxSerializationCore)
                implementation(libs.materialKolor)
                implementation(libs.colorPickerCompose)
            }
        }
        androidMain.dependencies {
            implementation(libs.composeUiTooling)
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
    dependsOn("kspCommonMainKotlinMetadata")
}
