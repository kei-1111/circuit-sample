rootProject.name = "circuit-sample"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        // Circuit 0.32.0-alpha02 (rememberSaveable状態保持バグ修正版) 用
        // https://github.com/slackhq/circuit/discussions/2455
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

include(":shared")
include(":core:common")
include(":core:local")
include(":core:model")
include(":core:navigation")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":feature:main")
include(":feature:settings")
include(":feature:oss")
include(":feature:detail")
include(":app-android")