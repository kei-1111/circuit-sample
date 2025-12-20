package io.github.kei_1111.circuit.sample.feature.main.main

import com.slack.circuit.runtime.CircuitUiState

data class MainState(
    val navItems: List<BottomNavItem>,
    val selectedIndex: Int,
    val eventSink: (MainEvent) -> Unit
) : CircuitUiState
