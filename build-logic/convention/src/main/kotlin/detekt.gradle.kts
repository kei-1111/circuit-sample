import io.github.kei_1111.circuit.sample.buildlogic.configureDetekt
import io.github.kei_1111.circuit.sample.buildlogic.detektPlugins
import io.github.kei_1111.circuit.sample.buildlogic.library
import io.github.kei_1111.circuit.sample.buildlogic.libs

plugins {
    id("io.gitlab.arturbosch.detekt")
}

configureDetekt()

dependencies {
    detektPlugins(libs.library("detektCompose"))
    detektPlugins(libs.library("detektFormatting"))
}