package io.github.kei_1111.circuit.sample.feature.main

import circuit_sample.feature.main.generated.resources.Res
import circuit_sample.feature.main.generated.resources.favorite
import circuit_sample.feature.main.generated.resources.home
import circuit_sample.feature.main.generated.resources.ic_favorite
import circuit_sample.feature.main.generated.resources.ic_home
import circuit_sample.feature.main.generated.resources.ic_more
import circuit_sample.feature.main.generated.resources.more
import com.slack.circuit.runtime.screen.Screen
import io.github.kei_1111.circuit.sample.core.navigation.FavoriteScreen
import io.github.kei_1111.circuit.sample.core.navigation.HomeScreen
import io.github.kei_1111.circuit.sample.core.navigation.MoreScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class BottomNavItem(
    val screen: Screen,
    val icon: DrawableResource,
    val labelRes: StringResource
) {
    data object Home : BottomNavItem(
        screen = HomeScreen,
        icon = Res.drawable.ic_home,
        labelRes = Res.string.home
    )

    data object Favorite : BottomNavItem(
        screen = FavoriteScreen,
        icon = Res.drawable.ic_favorite,
        labelRes = Res.string.favorite
    )

    data object More : BottomNavItem(
        screen = MoreScreen,
        icon = Res.drawable.ic_more,
        labelRes = Res.string.more
    )
}
