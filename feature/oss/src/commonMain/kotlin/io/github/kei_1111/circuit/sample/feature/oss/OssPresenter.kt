package io.github.kei_1111.circuit.sample.feature.oss

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import circuit_sample.feature.oss.generated.resources.Res
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.entity.Library
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import com.mikepenz.aboutlibraries.ui.compose.util.htmlReadyLicenseContent
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import io.github.kei_1111.circuit.sample.core.common.AppScope
import io.github.kei_1111.circuit.sample.core.navigation.OssScreen

data class OssState(
    val libraries: Libs?,
    val selectedLibrary: Library?,
    val eventSink: (OssEvent) -> Unit,
) : CircuitUiState

sealed interface OssEvent : CircuitUiEvent {
    data object NavigateBack : OssEvent
    data class ShowLicense(val library: Library) : OssEvent
    data object DismissLicense : OssEvent
}

class OssPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<OssState> {

    @CircuitInject(OssScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): OssPresenter
    }

    @Composable
    override fun present(): OssState {
        val libraries by produceLibraries { Res.readBytes("files/aboutlibraries.json").decodeToString() }
        var selectedLibrary by rememberRetained { mutableStateOf<Library?>(null) }

        return OssState(
            libraries = libraries,
            selectedLibrary = selectedLibrary,
            eventSink = { event ->
                when (event) {
                    OssEvent.NavigateBack -> navigator.pop()
                    is OssEvent.ShowLicense -> {
                        if (!event.library.licenses.firstOrNull()?.htmlReadyLicenseContent.isNullOrBlank()) {
                            selectedLibrary = event.library
                        }
                    }
                    OssEvent.DismissLicense -> selectedLibrary = null
                }
            }
        )
    }
}