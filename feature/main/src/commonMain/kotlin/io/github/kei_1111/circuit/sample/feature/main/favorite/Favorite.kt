package io.github.kei_1111.circuit.sample.feature.main.favorite

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.sharedelements.SharedElementTransitionScope
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.component.feature.UserItem
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.User
import io.github.kei_1111.circuit.sample.core.navigation.FavoriteScreen
import io.github.kei_1111.circuit.sample.feature.main.buildkonfig.BuildKonfig
import io.github.kei_1111.circuit.sample.feature.main.favorite.component.FavoriteTopAppBar

@OptIn(ExperimentalSharedTransitionApi::class)
@CircuitInject(FavoriteScreen::class, AppScope::class)
@Composable
fun Favorite(
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
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                SharedElementTransitionScope {
                    LazyColumn(
                        modifier = Modifier,
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.users) { user ->
                            UserItem(
                                user = user,
                                onClick = { state.eventSink(FavoriteEvent.NavigateDetail(user.id)) },
                                modifier = Modifier.fillMaxWidth(),
                                sharedElementTransitionScope = this@SharedElementTransitionScope
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun FavoritePreview() {
    CircuitSampleTheme {
        Favorite(
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