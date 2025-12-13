package io.github.kei_1111.circuit.sample.feature.main

import com.slack.circuit.runtime.screen.Screen
import io.github.kei_1111.circuit.sample.feature.main.favorite.FavoriteScreen
import io.github.kei_1111.circuit.sample.feature.main.home.HomeScreen
import org.jetbrains.compose.resources.DrawableResource
import circuit_sample.composeapp.generated.resources.Res
import circuit_sample.composeapp.generated.resources.ic_home
import circuit_sample.composeapp.generated.resources.ic_favorite

sealed class BottomNavItem(
    val screen: Screen,
    val icon: DrawableResource,
    val label: String
) {
    data object Home : BottomNavItem(
        screen = HomeScreen,
        icon = Res.drawable.ic_home,
        label = "Home"
    )

    data object Favorite : BottomNavItem(
        screen = FavoriteScreen,
        icon = Res.drawable.ic_favorite,
        label = "Favorite"
    )
}