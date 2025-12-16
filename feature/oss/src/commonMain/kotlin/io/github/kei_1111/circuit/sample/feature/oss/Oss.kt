package io.github.kei_1111.circuit.sample.feature.oss

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import circuit_sample.feature.oss.generated.resources.Res
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.m3.libraryColors
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import com.slack.circuit.codegen.annotations.CircuitInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.navigation.OssScreen
import io.github.kei_1111.circuit.sample.feature.oss.component.OssTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(OssScreen::class, AppScope::class)
@Composable
fun Oss(
    state: OssState,
    modifier: Modifier = Modifier,
) {
    val libraries by produceLibraries { Res.readBytes("files/aboutlibraries.json").decodeToString() }

    Scaffold(
        modifier = modifier,
        topBar = {
            OssTopAppBar(
                onClickBack = { state.eventSink(OssEvent.NavigateBack) }
            )
        }
    ) { innerPadding ->
        LibrariesContainer(
            libraries = libraries,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            libraryModifier = Modifier.clip(RoundedCornerShape(8.dp)),
            contentPadding = PaddingValues(16.dp),
            colors = LibraryDefaults.libraryColors(
                libraryBackgroundColor = MaterialTheme.colorScheme.surfaceContainer,
                libraryContentColor = MaterialTheme.colorScheme.onSurface,
            ),
            padding = LibraryDefaults.libraryPadding(
                contentPadding = PaddingValues(8.dp)
            ),
            dimensions = LibraryDefaults.libraryDimensions(
                itemSpacing = 16.dp
            ),
            textStyles = LibraryDefaults.libraryTextStyles(
                nameTextStyle = MaterialTheme.typography.titleMedium,
                versionTextStyle = MaterialTheme.typography.labelMedium,
                authorTextStyle = MaterialTheme.typography.labelSmall,
                licensesTextStyle = MaterialTheme.typography.labelMedium,
            ),
            shapes = LibraryDefaults.libraryShapes(
                chipShape = RoundedCornerShape(4.dp),
            ),
        )
    }
}

@Composable
@Preview
private fun OssPreview() {
    CircuitSampleTheme {
        Oss(
            state = OssState(
                eventSink = {}
            )
        )
    }
}