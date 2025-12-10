package io.github.kei_1111.circuit.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSample
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    CircuitSample {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Hello Circuit Sample",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}