package io.github.kei_1111.circuit.sample.feature.main.favorite.component

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Box
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
import io.github.kei_1111.circuit.sample.core.model.User
import io.github.kei_1111.circuit.sample.feature.main.buildkonfig.BuildKonfig
import io.github.kei_1111.circuit.sample.feature.main.favorite.FavoriteEvent
import io.github.kei_1111.circuit.sample.feature.main.favorite.FavoriteState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun FavoriteContent(
    state: FavoriteState,
    modifier: Modifier = Modifier,
) {
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        modifier = modifier,
        topBar = {
            FavoriteTopAppBar()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .padding(
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            if (state.isLoading) {
                FavoriteLoading()
            } else {
                FavoriteStable(
                    users = state.users,
                    onClickUser = { state.eventSink(FavoriteEvent.NavigateDetail(it.id)) }
                )
            }
        }
    }
}

@Composable
@Preview
private fun FavoriteContentPreview() {
    CircuitSampleTheme {
        FavoriteContent(
            state = FavoriteState(
                isLoading = false,
                users = List(30) { index ->
                    User(
                        id = "user_$index",
                        profileImageUrl = "${BuildKonfig.DRAWABLE_PATH}/img_profile_icon_preview.webp",
                        name = index.toString()
                    )
                },
                eventSink = {}
            )
        )
    }
}
