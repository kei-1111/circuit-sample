package io.github.kei_1111.circuit.sample.feature.main.main.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kei_1111.circuit.sample.core.designsystem.theme.CircuitSampleTheme
import io.github.kei_1111.circuit.sample.feature.main.main.BottomNavItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MainBottomNavigationBar(
    navItems: List<BottomNavItem>,
    selectedIndex: Int,
    onSelectTab: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onSelectTab(index) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.labelRes)
                    )
                },
                label = { Text(stringResource(item.labelRes)) }
            )
        }
    }
}

@Composable
@Preview
private fun MainBottomNavigationBarPreview() {
    CircuitSampleTheme {
        Surface {
            MainBottomNavigationBar(
                navItems = listOf(BottomNavItem.Home, BottomNavItem.Favorite, BottomNavItem.More),
                selectedIndex = 0,
                onSelectTab = {},
            )
        }
    }
}
