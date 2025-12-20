package io.github.kei_1111.circuit.sample.feature.main.more.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.feature.main.more.MoreEvent
import io.github.kei_1111.circuit.sample.feature.main.more.MoreState

@Composable
internal fun MoreContent(
    state: MoreState,
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        modifier = modifier,
        topBar = { MoreTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                )
        ) {
            MoreMenu(
                onClickSetting = { state.eventSink(MoreEvent.NavigateSettings) },
                onClickOss = { state.eventSink(MoreEvent.NavigateOss) },
            )
        }
    }
}

@Composable
@Preview
private fun MoreContentPreview() {
    CircuitSampleTheme {
        MoreContent(
            state = MoreState(
                eventSink = {}
            )
        )
    }
}
