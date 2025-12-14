# circuit-sample Naming Conventions

Follow these naming conventions when creating new files, classes, modules, or resources.

## Quick Reference

| Target | Pattern | Example |
|--------|---------|---------|
| Base Package | `io.github.kei_1111.circuit.sample` | - |
| Screen | `<Feature>Screen` | `HomeScreen`, `SettingsScreen` |
| Presenter | `<Feature>Presenter` | `HomePresenter`, `SettingsPresenter` |
| State | `<Feature>State` | `HomeState`, `SettingsState` |
| Event | `<Feature>Event` | `HomeEvent`, `SettingsEvent` |
| UseCase | `<Verb><Target>UseCase` | `GetThemeUseCase`, `SetSeedColorUseCase` |
| Repository | `<Entity>Repository[Impl]` | `UserPreferencesRepository`, `UserPreferencesRepositoryImpl` |
| Theme Function | `<ProjectName>Theme` | `CircuitSampleTheme` |
| DI Scope | `<Level>Scope` | `AppScope` |
| DI Graph | `<Level>Graph` | `AppGraph`, `PlatformGraph` |
| Icon | `ic_<name>` | `ic_home`, `ic_settings` |
| Callback | `on<Action><Target>` | `onClickSettings`, `onDismiss`, `onChangeColor` |

## Version Catalog (libs.versions.toml)

```toml
[versions]
androidCompileSdk = "36"        # camelCase

[libraries]
circuitFoundation = { ... }     # camelCase (hierarchical)

[plugins]
kotlinMultiplatform = { ... }   # camelCase
```

---

## Detailed Reference

### Package Names

**Base Package:**
```
io.github.kei_1111.circuit.sample
```

**Subpackage Structure:**
```
io.github.kei_1111.circuit.sample
├── core
│   ├── common          # Shared utilities (CommonParcelize)
│   ├── data            # Repository implementations
│   ├── designsystem    # Theme
│   ├── domain          # UseCases
│   ├── local           # DataStore
│   └── model           # Data models
├── di                  # DI config (AppGraph, AppScope)
└── feature
    ├── main
    │   ├── home
    │   └── favorite
    └── settings
```

---

### Circuit Pattern Naming

**Screen/State/Event/Presenter are defined in the same file:**

```kotlin
// HomePresenter.kt

@CommonParcelize
object HomeScreen : Screen

data class HomeState(
    val count: Int,
    val eventSink: (HomeEvent) -> Unit
) : CircuitUiState

sealed interface HomeEvent : CircuitUiEvent {
    data object Increase : HomeEvent
    data object Decrease : HomeEvent
    data object NavigateSettings : HomeEvent
}

class HomePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<HomeState> { ... }
```

**SideEffect (optional):**
```kotlin
sealed interface SettingsSideEffect {
    data class ShowSnackbar(val message: String) : SettingsSideEffect
}
```

---

### UseCase

Pattern: `<Verb><Target>UseCase`

| Verb | Usage | Example |
|------|-------|---------|
| `Get` | Retrieve data (Flow) | `GetThemeUseCase` |
| `Set` | Save data | `SetThemeUseCase` |
| `Fetch` | Remote fetch | `FetchArticlesUseCase` |
| `Update` | Update | `UpdateUserUseCase` |
| `Delete` | Delete | `DeleteArticleUseCase` |

```kotlin
class GetThemeUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke(): Flow<UserPreferences.Theme> =
        userPreferencesRepository.theme
}
```

---

### Repository

**Interface:** `<Entity>Repository`
**Implementation:** `<Entity>RepositoryImpl`

```kotlin
// UserPreferencesRepository.kt (interface)
interface UserPreferencesRepository {
    val theme: Flow<UserPreferences.Theme>
    suspend fun setTheme(theme: UserPreferences.Theme)
}

// UserPreferencesRepositoryImpl.kt (implementation)
class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository { ... }
```

---

### Composable Functions

**Screen UI:**

Pattern: Same name as feature (`Home`, `Settings`)
```kotlin
@CircuitInject(HomeScreen::class, AppScope::class)
@Composable
fun Home(
    state: HomeState,
    modifier: Modifier = Modifier,
) { ... }
```

**Components:**

Pattern: `<Feature><ComponentType>`
```kotlin
@Composable
fun HomeTopAppBar(...) { ... }

@Composable
fun SettingsSection(...) { ... }

@Composable
fun ColorPickerBottomSheet(...) { ... }
```

**Theme Function:**

Pattern: `<ProjectName>Theme`
```kotlin
@Composable
fun CircuitSampleTheme(
    seedColor: UserPreferences.SeedColor = UserPreferences.SeedColor.Default,
    theme: UserPreferences.Theme = UserPreferences.Theme.SYSTEM,
    content: @Composable () -> Unit
) { ... }
```

**Preview Function:**

Pattern: `<ComponentName>Preview` (private)
```kotlin
@Composable
@Preview
private fun SettingsPreview() { ... }
```

---

### DI/Metro

**Scope:**
```kotlin
abstract class AppScope private constructor()
```

**Graph:**
```kotlin
@SingleIn(AppScope::class)
@DependencyGraph(AppScope::class)
interface AppGraph {
    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Includes platformGraph: PlatformGraph): AppGraph
    }
}
```

**Provider Methods:**

Pattern: `provide<Component>`
```kotlin
@SingleIn(AppScope::class)
@Provides
fun provideCircuit(...): Circuit = ...

@SingleIn(AppScope::class)
@Provides
fun provideUserPreferencesRepository(...): UserPreferencesRepository = ...
```

---

### Callback Functions

Pattern: `on<Action><Target>`

- `<Target>` は操作対象を表す（自明な場合は省略可）
- `Icon`, `Button` などのUI要素名は自明なため省略

| Action | Usage | Example |
|--------|-------|---------|
| `Click` | Tap | `onClickSettings`, `onClickBack`, `onClickItem` |
| `Dismiss` | Close | `onDismiss` |
| `Change` | Value change | `onChangeColor`, `onChangeTheme` |
| `Select` | Selection | `onSelectTab`, `onSelectItem` |

```kotlin
@Composable
fun HomeTopAppBar(
    onClickSettings: () -> Unit,  // SettingsIconをクリック → Iconは自明なので省略
) { ... }

@Composable
fun SettingsTopAppBar(
    onClickBack: () -> Unit,      // BackIconをクリック → Iconは自明なので省略
) { ... }

@Composable
fun ColorPickerBottomSheet(
    initialColor: Color,
    onDismiss: () -> Unit,
    onChangeColor: (Color) -> Unit,
) { ... }
```

---

### Resources

**Icons:**

Pattern: `ic_<icon_name>` (snake_case)

```
ic_home
ic_favorite
ic_settings
ic_back
ic_add
ic_remove
ic_light_mode
ic_dark_mode
ic_system
```

**Location:**
```
composeApp/src/commonMain/composeResources/drawable/
```

---

### Models / Data Classes

**Sealed Interface Hierarchy:**
```kotlin
sealed interface UserPreferences {
    enum class Theme : UserPreferences {
        SYSTEM, LIGHT, DARK
    }

    sealed interface SeedColor : UserPreferences {
        val color: Color
        data object Default : SeedColor { ... }
        data class Custom(override val color: Color) : SeedColor
    }
}
```

---

### File Structure

**Feature Structure:**
```
feature/
├── settings/
│   ├── Settings.kt              # UI Composable
│   ├── SettingsPresenter.kt     # Screen + State + Event + Presenter
│   └── component/
│       ├── SettingsTopAppBar.kt
│       ├── SettingsSection.kt
│       └── ColorPickerBottomSheet.kt
```

**Core Structure:**
```
core/
├── common/CommonParcelize.kt
├── data/
│   ├── UserPreferencesRepository.kt
│   └── UserPreferencesRepositoryImpl.kt
├── domain/
│   ├── GetThemeUseCase.kt
│   └── SetThemeUseCase.kt
├── designsystem/Theme.kt
├── local/
│   ├── DataStoreFactory.kt
│   └── DataStorePathProducer.kt
└── model/UserPreferences.kt
```