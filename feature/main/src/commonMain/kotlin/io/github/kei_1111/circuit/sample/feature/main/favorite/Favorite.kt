package io.github.kei_1111.circuit.sample.feature.main.favorite

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.FavoriteScreen
import io.github.kei_1111.circuit.sample.feature.main.favorite.component.FavoriteContent

@OptIn(ExperimentalSharedTransitionApi::class)
@CircuitInject(FavoriteScreen::class, AppScope::class)
@Composable
fun Favorite(
    state: FavoriteState,
    modifier: Modifier = Modifier,
) {
    FavoriteContent(
        state = state,
        modifier = modifier.fillMaxSize()
    )
}
