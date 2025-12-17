package io.github.kei_1111.circuit.sample

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.sharedelements.SharedElementTransitionLayout
import dev.zacsweers.metro.Inject
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.core.domain.GetSeedColorUseCase
import io.github.kei_1111.circuit.sample.core.domain.GetThemeUseCase
import io.github.kei_1111.circuit.sample.core.model.UserPreferences
import io.github.kei_1111.circuit.sample.core.navigation.MainScreen
import io.github.kei_1111.circuit.sample.core.ui.PlatformBackHandler

@OptIn(ExperimentalSharedTransitionApi::class)
@Inject
class CircuitSampleApp(
    private val circuit: Circuit,
    private val getSeedColorUseCase: GetSeedColorUseCase,
    private val getThemeUseCase: GetThemeUseCase,
) {
    @Composable
    operator fun invoke(
        onRootPop: () -> Unit = {},
    ) {
        val backStack = rememberSaveableBackStack(MainScreen)
        val navigator = rememberCircuitNavigator(backStack) { _ -> onRootPop() }
        val theme by getThemeUseCase().collectAsState(UserPreferences.Theme.SYSTEM)
        val seedColor by getSeedColorUseCase().collectAsState(UserPreferences.SeedColor.Default)

        // MainScreenのコンテンツ(Home, Favorite, More)に関してはMain.ktのBackHandlerが処理するため、
        // ここはそれ以外のpopのみ
        PlatformBackHandler(enabled = backStack.size != 1) { navigator.pop() }

        CircuitSampleTheme(
            seedColor = seedColor,
            theme = theme
        ) {
            CircuitCompositionLocals(circuit) {
                SharedElementTransitionLayout {
                    Surface { NavigableCircuitContent(navigator, backStack) }
                }
            }
        }
    }
}
