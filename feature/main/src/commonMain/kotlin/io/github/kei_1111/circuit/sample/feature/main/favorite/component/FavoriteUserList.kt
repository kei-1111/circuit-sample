package io.github.kei_1111.circuit.sample.feature.main.favorite.component

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slack.circuit.sharedelements.SharedElementTransitionScope
import io.github.kei_1111.circuit.sample.core.designsystem.component.feature.UserItem
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.User
import io.github.kei_1111.circuit.sample.feature.main.buildkonfig.BuildKonfig

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun FavoriteUserList(
    users: List<User>,
    onClickUser: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    SharedElementTransitionScope {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(users) { user ->
                UserItem(
                    user = user,
                    onClick = { onClickUser(user) },
                    modifier = Modifier.fillMaxWidth(),
                    sharedElementTransitionScope = this@SharedElementTransitionScope
                )
            }
        }
    }
}

@Composable
@Preview
private fun FavoriteUserListPreview() {
    CircuitSampleTheme {
        Surface {
            FavoriteUserList(
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
