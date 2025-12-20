package io.github.kei_1111.circuit.sample.feature.oss

import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.entity.Library
import com.slack.circuit.runtime.CircuitUiState

data class OssState(
    val libraries: Libs?,
    val selectedLibrary: Library?,
    val eventSink: (OssEvent) -> Unit,
) : CircuitUiState
