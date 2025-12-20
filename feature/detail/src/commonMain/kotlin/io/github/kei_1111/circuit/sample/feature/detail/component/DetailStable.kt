package io.github.kei_1111.circuit.sample.feature.detail.component

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.feature.detail.buildkonfig.BuildKonfig

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun DetailStable(
    profileImageUrl: String,
    userName: String,
    userId: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        DetailProfile(
            profileImageUrl = profileImageUrl,
            userName = userName,
            userId = userId,
        )
    }
}

@Composable
@Preview
private fun DetailStablePreview() {
    CircuitSampleTheme {
        Surface {
            DetailStable(
                profileImageUrl = "${BuildKonfig.DRAWABLE_PATH}/img_profile_icon_preview.webp",
                userName = "No.1",
                userId = "",
            )
        }
    }
}
