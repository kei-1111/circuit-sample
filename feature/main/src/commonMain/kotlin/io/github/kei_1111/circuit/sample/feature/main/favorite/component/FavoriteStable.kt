package io.github.kei_1111.circuit.sample.feature.main.favorite.component

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.User
import io.github.kei_1111.circuit.sample.feature.main.buildkonfig.BuildKonfig

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun FavoriteStable(
    users: List<User>,
    onClickUser: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        FavoriteUserList(
            users = users,
            onClickUser = onClickUser
        )
    }
}

@Composable
@Preview
private fun FavoriteStablePreview() {
    CircuitSampleTheme {
        Surface {
            FavoriteStable(
                users = List(30) { index ->
                    User(
                        id = "user_$index",
                        profileImageUrl = "${BuildKonfig.DRAWABLE_PATH}/img_profile_icon_preview.webp",
                        name = index.toString()
                    )
                },
                onClickUser = {},
            )
        }
    }
}
