package io.github.kei_1111.circuit.sample.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import circuit_sample.composeapp.generated.resources.Res
import circuit_sample.composeapp.generated.resources.ic_add
import circuit_sample.composeapp.generated.resources.ic_remove
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.di.AppScope
import io.github.kei_1111.circuit.sample.feature.home.component.HomeTopAppBar
import org.jetbrains.compose.resources.painterResource

@CircuitInject(HomeScreen::class, AppScope::class)
@Composable
fun Home(
    state: HomeState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopAppBar(
                onClickSettings = { state.eventSink(HomeEvent.NavigateSettings) }
            )
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { state.eventSink(HomeEvent.Decrease) },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_remove),
                    contentDescription = null
                )
            }
            Text(
                text = "${state.count}",
                modifier = Modifier.widthIn(40.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
            IconButton(
                onClick = { state.eventSink(HomeEvent.Increase) },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview
private fun HomePreview() {
    Home(
        state = HomeState(
            count = 10,
            eventSink = {}
        )
    )
}
