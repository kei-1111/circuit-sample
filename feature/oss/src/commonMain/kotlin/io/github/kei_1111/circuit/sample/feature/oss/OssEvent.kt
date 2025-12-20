package io.github.kei_1111.circuit.sample.feature.oss

import com.mikepenz.aboutlibraries.entity.Library
import com.slack.circuit.runtime.CircuitUiEvent

sealed interface OssEvent : CircuitUiEvent {
    data object NavigateBack : OssEvent
    data class ShowLicense(val library: Library) : OssEvent
    data object DismissLicense : OssEvent
}
