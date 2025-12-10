package io.github.kei_1111.circuit.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSample
import io.github.kei_1111.circuit.sample.feature.home.HomePresenterFactory
import io.github.kei_1111.circuit.sample.feature.home.HomeScreen
import io.github.kei_1111.circuit.sample.feature.home.HomeUiFactory

@Composable
fun App() {
    val circuit = Circuit.Builder()
        .addPresenterFactory(HomePresenterFactory())
        .addUiFactory(HomeUiFactory())
        .build()

    CircuitSample {
        CircuitCompositionLocals(circuit) {
            CircuitContent(HomeScreen)
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}