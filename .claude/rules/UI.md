---
paths: feature/**/*.kt
---

# UI実装ガイドライン

## コンポーネントの責務分離

### 基本原則

コンポーネントはViewの責務のみに集中すべき。ビジネスロジックやEvent/Stateの詳細を知るべきではない。

### Eventの知識境界

| レイヤー | Eventの知識 | コールバックパターン |
|---------|-------------|---------------------|
| `*.kt` (Circuit UI) | あり | `state.eventSink(Event)` |
| `component/*.kt` | なし | UIコールバック (例: `onClickSettings`) |

### 構造例

```
Home.kt                    <- eventSink付きStateを受け取り、コールバックをEventにマッピング
└── HomeTopAppBar.kt       <- onClickSettings: () -> Unit (Eventの知識なし)
```

```
Settings.kt                <- eventSink付きStateを受け取る
├── SettingsTopAppBar.kt   <- onClickBack: () -> Unit
├── SettingsSection.kt     <- onClickItem: (T) -> Unit
└── ColorPickerBottomSheet.kt <- onDismiss, onChangeColor
```

---

## 実装パターン

### Screen UI (Circuit Inject)

Screen レベルのComposableは`State`を受け取り、`eventSink`でイベントをディスパッチする。

```kotlin
// Home.kt - Eventの知識あり
@CircuitInject(HomeScreen::class, AppScope::class)
@Composable
fun Home(
    state: HomeState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            HomeTopAppBar(
                // UIコールバックをEventにマッピング
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

### Components (Eventの知識なし)

コンポーネントはシンプルなコールバックを受け取る純粋なUI要素。

```kotlin
// HomeTopAppBar.kt - Eventの知識なし（純粋なUIコンポーネント）
@Composable
fun HomeTopAppBar(
    onClickSettings: () -> Unit,  // シンプルなコールバック、Event型なし
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

### 型パラメータ付き汎用コンポーネント

```kotlin
// SettingsSection.kt - 再利用可能、Eventの知識なし
@Composable
fun <T : UserPreferences> SettingsSection(
    title: String,
    items: List<T>,
    selectedItem: T,
    onClickItem: (T) -> Unit,  // アイテム型付き汎用コールバック
    modifier: Modifier = Modifier,
) {
    Column {
        items.forEach { item ->
            SettingItem(
                userPreferences = item,
                selected = item == selectedItem,
                onClick = { onClickItem(item) }  // 親に委譲
            )
        }
    }
}
```

---

## 利点

1. **テスト容易性**: UIコンポーネントはシンプルなコールバックでテスト可能
2. **再利用性**: Event依存のないコンポーネントは機能間で再利用可能
3. **関心の分離**: UIはViewに集中、ビジネスロジックはScreen/Presenterで処理

---

## ファイル構成

### Screen UIファイル (`Feature.kt`)

- `@CircuitInject`でアノテーション
- `State`パラメータを受け取る
- コンポーネントのコールバックを`eventSink(Event)`にマッピング
- `Scaffold`、レイアウト構造を含む

### コンポーネントファイル (`component/*.kt`)

- `component/`サブディレクトリに配置
- シンプルなコールバックパラメータを受け取る
- `Event`や`State`型の知識なし
- 単一のUI責務に集中

```
feature/home/
├── Home.kt                  # Screen UI (@CircuitInject)
├── HomePresenter.kt         # State + Event + Presenter
└── component/
    └── HomeTopAppBar.kt     # 純粋なUIコンポーネント
```

---

## プレビューガイドライン

### Screenプレビュー

```kotlin
@Composable
@Preview
private fun HomePreview() {
    CircuitSampleTheme {
        Home(
            state = HomeState(
                count = 10,
                eventSink = {}  // プレビュー用の空ラムダ
            )
        )
    }
}
```

### コンポーネントプレビュー

```kotlin
@Composable
@Preview
private fun HomeTopAppBarPreview() {
    CircuitSampleTheme {
        Surface {
            HomeTopAppBar(
                onClickSettings = {}  // プレビュー用の空ラムダ
            )
        }
    }
}
```

### 複数Stateのプレビュー (PreviewParameter)

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

## よくある間違い

| NG | OK |
|----|-----|
| コンポーネントが`State`を受け取る | コンポーネントは必要なデータのみ受け取る |
| コンポーネントが`eventSink`を呼ぶ | コンポーネントはシンプルなコールバックを使用 |
| コンポーネントが`*Event`をインポート | コンポーネントはEventの知識を持たない |
| Themeラッパーなしのプレビュー | `CircuitSampleTheme`でラップしたプレビュー |