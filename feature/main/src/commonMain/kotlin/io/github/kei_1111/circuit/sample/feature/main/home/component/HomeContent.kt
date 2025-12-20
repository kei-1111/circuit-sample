package io.github.kei_1111.circuit.sample.feature.main.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.feature.main.home.HomeEvent
import io.github.kei_1111.circuit.sample.feature.main.home.HomeState

@Composable
internal fun HomeContent(
    state: HomeState,
    modifier: Modifier = Modifier,
) {
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        modifier = modifier,
        topBar = { HomeTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HomeCounter(
                count = state.count,
                onClickDecrease = { state.eventSink(HomeEvent.Decrease) },
                onClickIncrease = { state.eventSink(HomeEvent.Increase) },
            )
        }
    }
}

@Composable
@Preview
private fun HomeContentPreview() {
    CircuitSampleTheme {
        HomeContent(
            state = HomeState(
                count = 10,
                eventSink = {}
            )
        )
    }
}
