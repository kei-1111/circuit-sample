package io.github.kei_1111.circuit.sample.feature.oss.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.mikepenz.aboutlibraries.entity.Library
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.m3.libraryColors
import com.mikepenz.aboutlibraries.ui.compose.util.strippedLicenseContent
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.feature.oss.OssEvent
import io.github.kei_1111.circuit.sample.feature.oss.OssState
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OssContent(
    state: OssState,
    modifier: Modifier = Modifier,
) {
    state.selectedLibrary?.let { library ->
        LicenseBottomSheet(
            libraryName = library.name,
            licenseNames = library.licenses.joinToString(" / ") { it.name },
            licenseContent = library.strippedLicenseContent,
            onDismiss = { state.eventSink(OssEvent.DismissLicense) },
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            OssTopAppBar(
                onClickBack = { state.eventSink(OssEvent.NavigateBack) }
            )
        }
    ) { innerPadding ->
        LibrariesContainer(
            libraries = state.libraries,
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
            onLibraryClick = { state.eventSink(OssEvent.ShowLicense(it)) },
            licenseDialogBody = null,
        )
    }
}

@Composable
@Preview
private fun OssContentPreview(
    @PreviewParameter(OssContentPPP::class) parameter: OssContentPreviewParameter,
) {
    CircuitSampleTheme {
        OssContent(
            state = OssState(
                libraries = null,
                selectedLibrary = parameter.library,
                eventSink = {}
            )
        )
    }
}

private data class OssContentPreviewParameter(
    val library: Library?
)

private class OssContentPPP : CollectionPreviewParameterProvider<OssContentPreviewParameter>(
    collection = listOf(
        OssContentPreviewParameter(
            library = null,
        ),
        OssContentPreviewParameter(
            library = Library(
                uniqueId = "",
                artifactVersion = null,
                name = "Circuit",
                description = null,
                website = null,
                developers = persistentListOf(),
                organization = null,
                scm = null,
            )
        )
    )
)
