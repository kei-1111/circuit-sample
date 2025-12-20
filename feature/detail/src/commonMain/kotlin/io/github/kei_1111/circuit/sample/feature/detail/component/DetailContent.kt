package io.github.kei_1111.circuit.sample.feature.detail.component

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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.User
import io.github.kei_1111.circuit.sample.feature.detail.DetailEvent
import io.github.kei_1111.circuit.sample.feature.detail.DetailState
import io.github.kei_1111.circuit.sample.feature.detail.buildkonfig.BuildKonfig

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun DetailContent(
    state: DetailState,
    modifier: Modifier = Modifier,
) {
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        modifier = modifier,
        topBar = {
            DetailTopAppBar(
                onClickBack = { state.eventSink(DetailEvent.NavigateBack) },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                ),
            contentAlignment = Alignment.Center,
        ) {
            when (state) {
                DetailState.Loading -> {
                    DetailLoading()
                }

                is DetailState.Error -> {
                    DetailError()
                }

                is DetailState.Stable -> {
                    DetailStable(
                        profileImageUrl = state.user.profileImageUrl,
                        userName = state.user.name,
                        userId = state.user.id,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun DetailContentPreview(
    @PreviewParameter(DetailContentPPP::class) parameter: DetailContentPreviewParameter
) {
    CircuitSampleTheme {
        DetailContent(
            state = parameter.state,
        )
    }
}

private data class DetailContentPreviewParameter(
    val state: DetailState,
)

private class DetailContentPPP : CollectionPreviewParameterProvider<DetailContentPreviewParameter> (
    collection = listOf(
        DetailContentPreviewParameter(
            state = DetailState.Loading
        ),
        DetailContentPreviewParameter(
            state = DetailState.Stable(
                user = User(
                    id = "",
                    profileImageUrl = "${BuildKonfig.DRAWABLE_PATH}/img_profile_icon_preview.webp",
                    name = "No.1"
                ),
                eventSink = {},
            )
        ),
        DetailContentPreviewParameter(
            state = DetailState.Error(
                eventSink = {},
            )
        )
    )
)
