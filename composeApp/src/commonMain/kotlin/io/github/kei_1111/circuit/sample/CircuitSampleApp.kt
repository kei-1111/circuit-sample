package io.github.kei_1111.circuit.sample

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.domain.GetSeedColorUseCase
import io.github.kei_1111.circuit.sample.core.domain.GetThemeUseCase
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.feature.home.HomeScreen

@Inject
class CircuitSampleApp(
    private val circuit: Circuit,
    private val getSeedColorUseCase: GetSeedColorUseCase,
    private val getThemeUseCase: GetThemeUseCase,
) {
    @Composable
    operator fun invoke() {
        val backStack = rememberSaveableBackStack(HomeScreen)
        val navigator = rememberCircuitNavigator(backStack) {}
        val theme by getThemeUseCase().collectAsState(UserPreferences.Theme.SYSTEM)
        val seedColor by getSeedColorUseCase().collectAsState(UserPreferences.SeedColor.Default)

        CircuitSampleTheme(
            seedColor = seedColor.color,
            theme = theme
        ) {
            CircuitCompositionLocals(circuit) {
                Surface { NavigableCircuitContent(navigator, backStack) }
            }
        }
    }
}