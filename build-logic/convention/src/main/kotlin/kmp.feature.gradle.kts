import io.github.kei_1111.circuit.sample.buildlogic.libs
import io.github.kei_1111.circuit.sample.buildlogic.library
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    id("kmp.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
    id("dev.zacsweers.metro")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-P",
            "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=io.github.kei_1111.circuit.sample.core.common.CommonParcelize"
        )
    }

    androidLibrary {
        androidResources.enable = true
    }

    sourceSets {
        commonMain {
            kotlin {
                srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            }
            dependencies {
                implementation(project(":core:common"))
                implementation(project(":core:navigation"))
                implementation(project(":core:model"))
                implementation(project(":core:domain"))
                implementation(project(":core:designsystem"))

                implementation(libs.library("circuitFoundation"))
                implementation(libs.library("circuitCodegenAnnotations"))
                implementation(libs.library("composeRuntime"))
                implementation(libs.library("composeFoundation"))
                implementation(libs.library("composeMaterial3"))
                implementation(libs.library("composeUi"))
                implementation(libs.library("composeUiToolingPreview"))
            }
        }

        androidMain.dependencies {
            implementation(libs.library("composeUiTooling"))
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.library("circuitCodegen"))
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (this is AbstractKotlinCompile<*>) {
        incremental = false
    }
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

ksp {
    arg("circuit.codegen.mode", "metro")
}