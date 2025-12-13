import com.google.devtools.ksp.gradle.KspExtension
import io.github.kei_1111.circuit.sample.buildlogic.debugImplementation
import io.github.kei_1111.circuit.sample.buildlogic.implementation
import io.github.kei_1111.circuit.sample.buildlogic.library
import io.github.kei_1111.circuit.sample.buildlogic.libs
import io.github.kei_1111.circuit.sample.buildlogic.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

class KmpFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("circuitSampleKmpLibrary").pluginId)
                apply(libs.plugin("composeMultiplatform").pluginId)
                apply(libs.plugin("composeCompiler").pluginId)
                apply(libs.plugin("ksp").pluginId)
                apply(libs.plugin("metro").pluginId)
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    commonMain {
                        kotlin {
                            srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                        }
                        dependencies {
                            implementation(libs.library("circuitFoundation"))
                            implementation(libs.library("circuitCodegenAnnotations"))
                            implementation(libs.library("composeRuntime"))
                            implementation(libs.library("composeFoundation"))
                            implementation(libs.library("composeMaterial3"))
                            implementation(libs.library("composeUi"))
                            implementation(libs.library("composeUiToolingPreview"))
                        }
                    }
                }
            }

            dependencies {
                debugImplementation(libs.library("composeUiTooling"))
                add("kspCommonMainMetadata", libs.library("circuitCodegen"))
            }

            tasks.withType(KotlinCompilationTask::class.java).configureEach {
                if (this is AbstractKotlinCompile<*>) {
                    incremental = false
                }
                dependsOn("kspCommonMainKotlinMetadata")
            }

            extensions.configure<KspExtension> {
                arg("circuit.codegen.mode", "metro")
            }
        }
    }
}