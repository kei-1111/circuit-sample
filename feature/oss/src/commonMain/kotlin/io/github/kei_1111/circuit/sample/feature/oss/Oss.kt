package io.github.kei_1111.circuit.sample.feature.oss

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import circuit_sample.feature.oss.generated.resources.Res
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.navigation.OssScreen
import io.github.kei_1111.circuit.sample.feature.oss.component.OssTopAppBar

@CircuitInject(OssScreen::class, AppScope::class)
@Composable
fun Oss(
    state: OssState,
    modifier: Modifier = Modifier,
) {
    val libraries by produceLibraries { Res.readBytes("files/aboutlibraries.json").decodeToString() }

    Scaffold(
        modifier = modifier,
        topBar = {
            OssTopAppBar(
                onClickBack = { state.eventSink(OssEvent.NavigateBack) }
            )
        }
    ) { innerPadding ->
        LibrariesContainer(
            libraries = libraries,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
@Preview
private fun OssPreview() {
    CircuitSampleTheme {
        Oss(
            state = OssState(
                eventSink = {}
            )
        )
    }
}