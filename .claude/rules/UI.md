---
paths: feature/**/*.kt
---

# UI実装ガイドライン

## コンポーネントの責務分離

### 基本原則

3層構造でUIを構築する：
1. **Screen UI** - Circuitとの接続点（薄いレイヤー）
2. **Content Component** - レイアウト構造とイベントマッピング
3. **Sub Components** - 純粋なUIコンポーネント

### Eventの知識境界

| レイヤー | ファイル | Eventの知識 | 役割 |
|---------|----------|-------------|------|
| Screen UI | `Feature.kt` | あり | SideEffect処理、State渡し |
| Content | `component/FeatureContent.kt` | あり | Scaffold、状態分岐、eventSinkマッピング |
| Sub Components | `component/*.kt` | なし | 純粋なUI（コールバック受け取り） |

### 構造例

```
feature/detail/
├── Detail.kt                  <- Screen UI (@CircuitInject)、薄いレイヤー
├── DetailPresenter.kt         <- Presenter
├── DetailState.kt             <- Sealed State定義
├── DetailEvent.kt             <- Event定義
└── component/
    ├── DetailContent.kt       <- Scaffold、状態分岐、eventSink使用
    ├── DetailTopAppBar.kt     <- onClickBack: () -> Unit
    ├── DetailLoading.kt       <- 純粋なUI
    ├── DetailError.kt         <- 純粋なUI
    └── DetailStable.kt        <- 純粋なUI（データのみ受け取り）
```

---

## State パターン

### Data Class State（単一状態）

画面全体の切り替えが不要な場合に使用。

```kotlin
// HomeState.kt - シンプルな状態
data class HomeState(
    val count: Int,
    val eventSink: (HomeEvent) -> Unit,
) : CircuitUiState
```

```kotlin
// SettingsState.kt - 複数プロパティ
data class SettingsState(
    val theme: UserPreferences.Theme = UserPreferences.Theme.SYSTEM,
    val seedColor: UserPreferences.SeedColor = UserPreferences.SeedColor.Default,
    val showColorPicker: Boolean = false,
    val canSave: Boolean = false,
    val sideEffect: SettingsSideEffect? = null,
    val eventSink: (SettingsEvent) -> Unit,
) : CircuitUiState
```

### Sealed Interface State（複数状態）

画面全体を切り替える場合（Loading→Content等）に使用。

```kotlin
// DetailState.kt
sealed interface DetailState : CircuitUiState {
    val eventSink: (DetailEvent) -> Unit

    data object Loading : DetailState {
        override val eventSink: (DetailEvent) -> Unit = {}
    }

    data class Stable(
        val user: User,
        override val eventSink: (DetailEvent) -> Unit,
    ) : DetailState

    data class Error(
        override val eventSink: (DetailEvent) -> Unit,
    ) : DetailState
}
```

### 使い分け

| パターン | 使用場面 | 例 |
|---------|---------|-----|
| Data Class | 画面全体の切り替えが不要 | Settings, Home |
| Sealed Interface | 画面全体を切り替える（Loading→Content等） | Detail, Favorite |

**判断基準**: 非同期処理の有無ではなく、**画面全体が別のUIに切り替わるか**で判断する。

---

## 実装パターン

### Screen UI (Circuit Inject) - 薄いレイヤー

```kotlin
// Detail.kt - SideEffectがない場合はStateを渡すだけ
@CircuitInject(DetailScreen::class, AppScope::class)
@Composable
fun Detail(
    state: DetailState,
    modifier: Modifier = Modifier,
) {
    DetailContent(
        state = state,
        modifier = modifier.fillMaxSize(),
    )
}
```

```kotlin
// Settings.kt - SideEffectがある場合
@CircuitInject(SettingsScreen::class, AppScope::class)
@Composable
fun Settings(
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // SideEffect処理
    LaunchedEffect(state.sideEffect) {
        when (val effect = state.sideEffect) {
            is SettingsSideEffect.ShowSnackbar -> {
                scope.launch { snackbarHostState.showSnackbar(getString(effect.messageRes)) }
                state.eventSink(SettingsEvent.ClearSideEffect)
            }
            null -> {}
        }
    }

    SettingsContent(
        state = state,
        snackbarHostState = snackbarHostState,
        modifier = modifier,
    )
}
```

### Content Component (Eventの知識あり)

```kotlin
// DetailContent.kt - Scaffold、状態分岐、eventSinkマッピング
@Composable
internal fun DetailContent(
    state: DetailState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            DetailTopAppBar(
                // UIコールバックをEventにマッピング
                onClickBack = { state.eventSink(DetailEvent.NavigateBack) },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            // Sealed Stateによる分岐
            when (state) {
                DetailState.Loading -> DetailLoading()
                is DetailState.Error -> DetailError()
                is DetailState.Stable -> {
                    DetailStable(
                        profileImageUrl = state.user.profileImageUrl,
                        userName = state.user.name,
                        userId = state.user.id,
                    )
                }
            }
        }
    }
}
```

### State別コンポーネント (Eventの知識なし)

```kotlin
// DetailLoading.kt - 純粋なUI
@Composable
internal fun DetailLoading(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(modifier = modifier)
}
```

```kotlin
// DetailStable.kt - データのみ受け取る純粋なUI
@Composable
internal fun DetailStable(
    profileImageUrl: String,
    userName: String,
    userId: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        // 純粋なUI実装
    }
}
```

### Sub Components (Eventの知識なし)

**純粋なUIコンポーネントの原則:**
- **Stateを持たない** - 表示に必要なデータはすべて引数で受け取る
- **Viewに徹する** - ビジネスロジックを含まない
- **アクションはコールバックで親に伝える** - 自身で処理しない

```kotlin
// DetailTopAppBar.kt - シンプルなコールバック
@Composable
internal fun DetailTopAppBar(
    onClickBack: () -> Unit,  // アクションはコールバックで親に伝える
    modifier: Modifier = Modifier,
) {
    // Stateを持たず、Viewに徹する
    CenterAlignedTopAppBar(
        title = { Text("詳細") },
        navigationIcon = {
            IconButton(onClick = onClickBack) {  // 自身で処理せず親に委譲
                Icon(painter = painterResource(Res.drawable.ic_back), null)
            }
        }
    )
}
```

```kotlin
// DetailStable.kt - データのみ受け取り、アクションはコールバック
@Composable
internal fun DetailStable(
    profileImageUrl: String,      // 表示データ
    userName: String,             // 表示データ
    userId: String,               // 表示データ
    onClickFollow: () -> Unit,    // アクションはコールバック
    modifier: Modifier = Modifier,
) {
    // Stateを持たない純粋なUI
    Column(modifier = modifier) {
        AsyncImage(model = profileImageUrl, ...)
        Text(text = userName)
        Button(onClick = onClickFollow) {  // 親に委譲
            Text("フォロー")
        }
    }
}
```

---

## ファイル構成

### Screen UIファイル (`Feature.kt`)

- `@CircuitInject`でアノテーション
- `State`を受け取り、Contentに渡す
- SideEffect処理（Snackbar、ダイアログ等）を担当
- **薄いレイヤー**に保つ

### Contentファイル (`component/FeatureContent.kt`)

- `Scaffold`、レイアウト構造を含む
- `State`と`eventSink`を使用
- `when`でSealed State分岐
- コンポーネントのコールバックを`eventSink(Event)`にマッピング

### State別コンポーネント (`component/Feature{Loading,Error,Stable}.kt`)

- Sealed Stateの各状態に対応
- 純粋なUI（必要なデータのみ受け取る）
- `Event`や`State`型の知識なし

### その他コンポーネント (`component/*.kt`)

- シンプルなコールバックパラメータを受け取る
- `Event`や`State`型の知識なし
- 単一のUI責務に集中

---

## プレビューガイドライン

### Contentプレビュー (PreviewParameter)

```kotlin
@Composable
@Preview
private fun DetailContentPreview(
    @PreviewParameter(DetailContentPPP::class) parameter: DetailContentPreviewParameter
) {
    CircuitSampleTheme {
        DetailContent(state = parameter.state)
    }
}

private data class DetailContentPreviewParameter(
    val state: DetailState,
)

private class DetailContentPPP : CollectionPreviewParameterProvider<DetailContentPreviewParameter>(
    collection = listOf(
        DetailContentPreviewParameter(state = DetailState.Loading),
        DetailContentPreviewParameter(
            state = DetailState.Stable(
                user = User(id = "", name = "Sample", profileImageUrl = "..."),
                eventSink = {},
            )
        ),
        DetailContentPreviewParameter(
            state = DetailState.Error(eventSink = {})
        )
    )
)
```

### コンポーネントプレビュー

```kotlin
@Composable
@Preview
private fun DetailStablePreview() {
    CircuitSampleTheme {
        Surface {
            DetailStable(
                profileImageUrl = "...",
                userName = "No.1",
                userId = "user_id",
            )
        }
    }
}
```

---

## 利点

1. **テスト容易性**: State別コンポーネントはシンプルなデータでテスト可能
2. **再利用性**: Event依存のないコンポーネントは機能間で再利用可能
3. **関心の分離**: Screen UIはSideEffect、ContentはUI構造、Sub Componentsは純粋なUI
4. **状態管理の明確化**: Sealed Stateで状態遷移が明確

---

## よくある間違い

| NG | OK |
|----|-----|
| Screen UIにScaffoldを配置 | ContentにScaffoldを配置 |
| Sub Componentに`State`型を渡す | 必要なデータのみ渡す |
| Sub Componentが`eventSink`を呼ぶ | シンプルなコールバックを使用 |
| Sub Componentが`*Event`をインポート | Eventの知識を持たない |
| Themeラッパーなしのプレビュー | `CircuitSampleTheme`でラップ |

**注意**: Contentで`state`にアクセスすることは問題ない。禁止されるのはSub Componentに`State`型を渡すこと。