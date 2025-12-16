package io.github.kei_1111.circuit.sample.feature.detail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.sharedelements.SharedElementTransitionScope
import com.slack.circuit.sharedelements.SharedElementTransitionScope.AnimatedScope.Navigation
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.User
import io.github.kei_1111.circuit.sample.core.navigation.DetailScreen
import io.github.kei_1111.circuit.sample.feature.detail.buildkonfig.BuildKonfig
import io.github.kei_1111.circuit.sample.feature.detail.component.DetailTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@CircuitInject(DetailScreen::class, AppScope::class)
@Composable
fun Detail(
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
                    CircularProgressIndicator()
                }

                is DetailState.Error -> {
                    Text(
                        text = "User not found",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                is DetailState.Stable -> {
                    SharedElementTransitionScope {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            AsyncImage(
                                model = state.user.profileImageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .sharedElement(
                                        sharedContentState = rememberSharedContentState(key = "user_image_${state.user.id}"),
                                        animatedVisibilityScope = requireAnimatedScope(Navigation),
                                    )
                                    .size(120.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = state.user.name,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.headlineMedium,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = state.user.id,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp),
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
private fun DetailPreview(
    @PreviewParameter(DetailPPP::class) parameter: DetailPreviewParameter
) {
    CircuitSampleTheme {
        Detail(
            state = parameter.state,
        )
    }
}

private data class DetailPreviewParameter(
    val state: DetailState,
)

private class DetailPPP : CollectionPreviewParameterProvider<DetailPreviewParameter> (
    collection = listOf(
        DetailPreviewParameter(
            state = DetailState.Loading
        ),
        DetailPreviewParameter(
            state = DetailState.Stable(
                user = User(
                    id = "",
                    profileImageUrl = "${BuildKonfig.DRAWABLE_PATH}/img_profile_icon_preview.webp",
                    name = "No.1"
                ),
                eventSink = {},
            )
        ),
        DetailPreviewParameter(
            state = DetailState.Error(
                eventSink = {},
            )
        )
    )
)
