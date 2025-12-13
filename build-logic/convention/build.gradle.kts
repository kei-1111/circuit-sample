import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    `kotlin-dsl`
}

group = "io.github.kei_1111.circuit.sample.buildlogic"

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    compileOnly(libs.androidGradle)
    compileOnly(libs.kotlinGradle)
    compileOnly(libs.composeGradle)
    compileOnly(libs.kspGradle)
    compileOnly(libs.metroGradle)
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = libs.plugins.circuitSampleKmpLibrary.get().pluginId
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("kmpFeature") {
            id = libs.plugins.circuitSampleKmpFeature.get().pluginId
            implementationClass = "KmpFeatureConventionPlugin"
        }
    }
}