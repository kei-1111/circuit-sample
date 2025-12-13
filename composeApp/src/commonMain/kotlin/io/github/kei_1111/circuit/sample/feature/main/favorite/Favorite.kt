package io.github.kei_1111.circuit.sample.feature.main.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.di.AppScope

@CircuitInject(FavoriteScreen::class, AppScope::class)
@Composable
fun Favorite(
    state: FavoriteState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "お気に入り",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview
private fun FavoritePreview() {
    Favorite(
        state = FavoriteState(
            eventSink = {}
        )
    )
}