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
import io.github.kei_1111.circuit.sample.core.data.UserPreferencesRepository
import io.github.kei_1111.circuit.sample.core.designsystem.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.feature.home.HomeScreen

@Inject
class CircuitSampleApp(
    private val circuit: Circuit,
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    @Composable
    operator fun invoke() {
        val backStack = rememberSaveableBackStack(HomeScreen)
        val navigator = rememberCircuitNavigator(backStack) {}
        val themeConfig by userPreferencesRepository.theme.collectAsState(UserPreferences.Theme.SYSTEM)

        CircuitSampleTheme(theme = themeConfig) {
            CircuitCompositionLocals(circuit) {
                Surface { NavigableCircuitContent(navigator, backStack) }
            }
        }
    }
}