import com.android.build.api.dsl.androidLibrary
import io.github.kei_1111.circuit.sample.buildlogic.libs
import io.github.kei_1111.circuit.sample.buildlogic.plugin
import io.github.kei_1111.circuit.sample.buildlogic.versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("kotlinMultiplatformLibrary").pluginId)
                apply(libs.plugin("kotlinMultiplatform").pluginId)
            }

            extensions.configure<KotlinMultiplatformExtension> {
                androidLibrary {
                    compileSdk = libs.versions("androidCompileSdk").toInt()
                    minSdk = libs.versions("androidMinSdk").toInt()

                    compilations.configureEach {
                        compilerOptions.configure {
                            jvmTarget.set(JvmTarget.JVM_21)
                        }
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
        }
    }
}