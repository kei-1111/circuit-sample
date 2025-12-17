package io.github.kei_1111.circuit.sample.core.designsystem.component.feature

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.slack.circuit.sharedelements.SharedElementTransitionScope
import com.slack.circuit.sharedelements.SharedElementTransitionScope.AnimatedScope.Navigation
import io.github.kei_1111.circuit.sample.core.designsystem.buildkonfig.BuildKonfig
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.User

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserItem(
    user: User,
    sharedElementTransitionScope: SharedElementTransitionScope?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 8.dp)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = user.profileImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .then(
                        if (sharedElementTransitionScope != null) {
                            with(sharedElementTransitionScope) {
                                Modifier
                                    .sharedElement(
                                        sharedContentState = rememberSharedContentState("user_image_${user.id}"),
                                        animatedVisibilityScope = requireAnimatedScope(Navigation),
                                    )
                            }
                        } else {
                            Modifier
                        }
                    )
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = user.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
@Preview
private fun UserItemPreview() {
    CircuitSampleTheme {
        Surface {
            UserItem(
                user = User(
                    id = "",
                    profileImageUrl = "${BuildKonfig.DRAWABLE_PATH}/img_profile_icon_preview.webp",
                    name = "No.1"
                ),
                sharedElementTransitionScope = null,
                onClick = {}
            )
        }
    }
}
