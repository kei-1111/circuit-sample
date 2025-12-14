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
| Event Members | `動詞 + 対象` | `NavigateSettings`, `ChangeTheme` |
| UseCase | `<Verb><Target>UseCase` | `GetThemeUseCase`, `SetSeedColorUseCase` |
| Repository | `<Entity>Repository[Impl]` | `UserPreferencesRepository`, `UserPreferencesRepositoryImpl` |
| Composable (Screen) | `<Feature>` | `Home`, `Settings` |
| Composable (Component) | `<Feature><ComponentType>` | `HomeTopAppBar`, `SettingsSection` |
| Theme Function | `<ProjectName>Theme` | `CircuitSampleTheme` |
| DI Scope | `<Level>Scope` | `AppScope` |
| DI Graph | `<Platform>AppGraph` | `AndroidAppGraph`, `IosAppGraph` |
| Provider Method | `provide<Component>` | `provideCircuit`, `provideUserPreferencesRepository` |
| Callback | `on<Action><Target>` | `onClickSettings`, `onDismiss`, `onChangeColor` |
| Icon | `ic_<name>` | `ic_home`, `ic_settings` |

---

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

## Event 命名規則

パターン: `動詞 + 対象`（意図ベース＝「何をしたいか」）

```kotlin
// Good: 意図ベース（何をしたいか）
NavigateSettings, ChangeCategory, RefreshArticles, ShareArticle

// Bad: 操作ベース（禁止）
OnClickSettingsButton, OnSwipeRefresh, OnLongPressArticle

// Bad: 選択ベース（意図が不明確）
SelectArticle, Refresh
```

**理由:**
- 操作ベースだと同じ処理を行う異なる操作（スワイプ/タップ）で重複が発生
- `SelectArticle`より`NavigateViewer`の方が「ビューアに遷移したい」という意図が明確

---

## UseCase 命名規則

パターン: `<Verb><Target>UseCase`

| Verb | Usage | Example |
|------|-------|---------|
| `Get` | Retrieve data (Flow) | `GetThemeUseCase` |
| `Set` | Save data | `SetThemeUseCase` |
| `Fetch` | Remote fetch | `FetchArticlesUseCase` |
| `Update` | Update | `UpdateUserUseCase` |
| `Delete` | Delete | `DeleteArticleUseCase` |

---

## Callback 命名規則

パターン: `on<Action><Target>`

- `<Target>` は操作対象を表す（自明な場合は省略可）
- `Icon`, `Button` などのUI要素名は自明なため省略

| Action | Usage | Example |
|--------|-------|---------|
| `Click` | Tap | `onClickSettings`, `onClickBack` |
| `Dismiss` | Close | `onDismiss` |
| `Change` | Value change | `onChangeColor`, `onChangeTheme` |
| `Select` | Selection | `onSelectTab`, `onSelectItem` |