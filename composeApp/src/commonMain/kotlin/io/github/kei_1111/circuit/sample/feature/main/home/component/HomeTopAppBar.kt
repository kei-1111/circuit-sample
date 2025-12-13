package io.github.kei_1111.circuit.sample.feature.main.home.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import circuit_sample.composeapp.generated.resources.Res
import circuit_sample.composeapp.generated.resources.ic_settings
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onClickSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Circuit Sample",
            )
        },
        actions = {
            IconButton(
                onClick = onClickSettings,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_settings),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
@Preview
private fun HomeTopAppBarPreview() {
    HomeTopAppBar(
        onClickSettings = {}
    )
}