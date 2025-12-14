package io.github.kei_1111.circuit.sample.feature.main.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.navigation.FavoriteScreen
import io.github.kei_1111.circuit.sample.feature.main.favorite.component.FavoriteTopAppBar

@CircuitInject(FavoriteScreen::class, AppScope::class)
@Composable
fun Favorite(
    state: FavoriteState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            FavoriteTopAppBar()
        }
    ) { innerPadding ->

    }
}

@Composable
@Preview
private fun FavoritePreview() {
    CircuitSampleTheme {
        Favorite(
            state = FavoriteState(
                eventSink = {}
            )
        )
    }
}