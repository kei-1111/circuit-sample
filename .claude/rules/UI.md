---
paths: feature/**/*.kt
---

# UI Implementation Guidelines

## Component Responsibility Separation

### Basic Principle

Components should focus on View responsibilities only. They should NOT have knowledge of business logic or Event/State details.

### Event Knowledge Boundary

| Layer | Event Knowledge | Callback Pattern |
|-------|-----------------|------------------|
| `*.kt` (Circuit UI) | Yes | `state.eventSink(Event)` |
| `component/*.kt` | No | UI callbacks (e.g., `onClickSettings`) |

### Structure Example

```
Home.kt                    <- Receives State with eventSink, maps callbacks to Events
└── HomeTopAppBar.kt       <- onClickSettings: () -> Unit (no Event knowledge)
```

```
Settings.kt                <- Receives State with eventSink
├── SettingsTopAppBar.kt   <- onClickBack: () -> Unit
├── SettingsSection.kt     <- onClickItem: (T) -> Unit
└── ColorPickerBottomSheet.kt <- onDismiss, onChangeColor
```

---

## Implementation Patterns

### Screen UI (Circuit Inject)

Screen-level composables receive `State` and use `eventSink` to dispatch events.

```kotlin
// Home.kt - Has Event knowledge
@CircuitInject(HomeScreen::class, AppScope::class)
@Composable
fun Home(
    state: HomeState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            HomeTopAppBar(
                // Map UI callback to Event
                onClickSettings = { state.eventSink(HomeEvent.NavigateSettings) }
            )
        }
    ) { innerPadding ->
        IconButton(
            onClick = { state.eventSink(HomeEvent.Increase) },
        ) { ... }
    }
}
```

### Components (No Event Knowledge)

Components are pure UI elements that receive simple callbacks.

```kotlin
// HomeTopAppBar.kt - No Event knowledge (pure UI component)
@Composable
fun HomeTopAppBar(
    onClickSettings: () -> Unit,  // Simple callback, no Event type
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text("Circuit Sample") },
        actions = {
            IconButton(onClick = onClickSettings) {
                Icon(painter = painterResource(Res.drawable.ic_settings), ...)
            }
        }
    )
}
```

### Generic Components with Type Parameters

```kotlin
// SettingsSection.kt - Reusable, no Event knowledge
@Composable
fun <T : UserPreferences> SettingsSection(
    title: String,
    items: List<T>,
    selectedItem: T,
    onClickItem: (T) -> Unit,  // Generic callback with item type
    modifier: Modifier = Modifier,
) {
    Column {
        items.forEach { item ->
            SettingItem(
                userPreferences = item,
                selected = item == selectedItem,
                onClick = { onClickItem(item) }  // Delegate to parent
            )
        }
    }
}
```

---

## Benefits

1. **Testability**: UI components can be tested with simple callbacks
2. **Reusability**: Components without Event dependency can be reused across features
3. **Separation of Concerns**: UI focuses on View, business logic handled in Screen/Presenter

---

## File Organization

### Screen UI File (`Feature.kt`)

- Annotated with `@CircuitInject`
- Receives `State` parameter
- Maps component callbacks to `eventSink(Event)`
- Contains `Scaffold`, layout structure

### Component Files (`component/*.kt`)

- Located in `component/` subdirectory
- Receive simple callback parameters
- No knowledge of `Event` or `State` types
- Focus on single UI responsibility

```
feature/home/
├── Home.kt                  # Screen UI (@CircuitInject)
├── HomePresenter.kt         # Screen + State + Event + Presenter
└── component/
    └── HomeTopAppBar.kt     # Pure UI component
```

---

## Preview Guidelines

### Screen Preview

```kotlin
@Composable
@Preview
private fun HomePreview() {
    CircuitSampleTheme {
        Home(
            state = HomeState(
                count = 10,
                eventSink = {}  // Empty lambda for preview
            )
        )
    }
}
```

### Component Preview

```kotlin
@Composable
@Preview
private fun HomeTopAppBarPreview() {
    CircuitSampleTheme {
        Surface {
            HomeTopAppBar(
                onClickSettings = {}  // Empty lambda for preview
            )
        }
    }
}
```

### PreviewParameter for Multiple States

```kotlin
@Composable
@Preview
private fun SettingItemPreview(
    @PreviewParameter(SettingsItemPPP::class) parameter: SettingsItemPreviewParameter
) {
    CircuitSampleTheme {
        Surface {
            SettingItem(
                userPreferences = parameter.userPreferences,
                selected = parameter.selected,
                onClick = {}
            )
        }
    }
}

private data class SettingsItemPreviewParameter(
    val userPreferences: UserPreferences,
    val selected: Boolean,
)

private class SettingsItemPPP : CollectionPreviewParameterProvider<SettingsItemPreviewParameter>(
    collection = listOf(
        SettingsItemPreviewParameter(UserPreferences.Theme.SYSTEM, true),
        SettingsItemPreviewParameter(UserPreferences.Theme.LIGHT, false),
        // ...
    )
)
```

---

## Common Mistakes

| Wrong | Correct |
|-------|---------|
| Component receives `State` | Component receives only needed data |
| Component calls `eventSink` | Component uses simple callbacks |
| Component imports `*Event` | Component has no Event knowledge |
| Preview without Theme wrapper | Preview wrapped with `CircuitSampleTheme` |