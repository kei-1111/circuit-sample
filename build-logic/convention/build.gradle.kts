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
    implementation(libs.androidGradle)
    implementation(libs.androidKmpLibraryGradle)
    implementation(libs.kotlinGradle)
    implementation(libs.composeCompilerGradle)
    implementation(libs.composeGradle)
    implementation(libs.detektGradle)
    implementation(libs.kspGradle)
    implementation(libs.metroGradle)
    implementation(libs.buildKonfigGradle)
    // FieldSpec.Type.STRINGのimportに必要 (https://github.com/yshrsmz/BuildKonfig/issues/227)
    implementation(libs.buildKonfigCompiler)
}