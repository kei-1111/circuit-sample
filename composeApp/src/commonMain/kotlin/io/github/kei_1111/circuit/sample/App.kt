package io.github.kei_1111.circuit.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSample

@Composable
fun App() {
    CircuitSample {
        CircuitSampleNavDisplay()
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}