import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    id("com.codingfeline.buildkonfig")
}

configure<BuildKonfigExtension> {
    // 各モジュール固有のパッケージ名を生成（重複回避）
    val modulePath = project.path.removePrefix(":").replace(":", ".")
    packageName = "io.github.kei_1111.circuit.sample.$modulePath.buildkonfig"

    defaultConfigs {
        buildConfigField(STRING, "DRAWABLE_PATH", "$projectDir/src/commonMain/composeResources/drawable")
    }
}