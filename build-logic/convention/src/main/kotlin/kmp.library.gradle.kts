import io.github.kei_1111.circuit.sample.buildlogic.libs
import io.github.kei_1111.circuit.sample.buildlogic.versions
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("detekt")
}

kotlin {
    androidLibrary {
        compileSdk = libs.versions("androidCompileSdk").toInt()
        minSdk = libs.versions("androidMinSdk").toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = project.name.replaceFirstChar { it.uppercase() }
            isStatic = true
        }
    }
}