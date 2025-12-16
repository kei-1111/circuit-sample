import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    id("com.codingfeline.buildkonfig")
}

configure<BuildKonfigExtension> {
    packageName = "io.github.kei_1111.circuit.sample.buildkonfig"

    defaultConfigs {
        buildConfigField(STRING, "DRAWABLE_PATH", "$projectDir/src/commonMain/composeResources/drawable")
    }
}