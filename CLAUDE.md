# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Top-Level Rules

- To maximize efficiency, **if you need to execute multiple independent processes, invoke those tools concurrently, not sequentially**.
- **You must think exclusively in English**. However, you are required to **respond in Japanese**.

## WHAT: プロジェクト概要

Compose Multiplatform + Circuit (Slack) によるサンプルアプリ。Clean Architecture + Circuitパターンを採用。

- **Circuit**: Slackが開発したリアクティブUIアーキテクチャ
- **Metro**: コード生成ベースのDIフレームワーク（ZacSweers作）
- **DataStore**: 設定値の永続化

## WHY: 設計原則

### 絶対に守るべきルール

1. **PresenterはUseCaseを経由** - Repositoryを直接使用しない
2. **UIはステートレス** - Stateを受け取り、eventSinkでイベント通知
3. **State/Event/Presenterは同一ファイル** - `XxxPresenter.kt`に定義
4. **Screenは`core/navigation`に集約** - `Screens.kt`に全Screen定義

### よくある間違い

- ❌ UIでビジネスロジック → ✅ Presenterで処理
- ❌ Stateを直接変更 → ✅ eventSinkでイベント発行
- ❌ Presenterで`repository.xxx()`呼び出し → ✅ UseCase経由

## HOW: 開発方法

### よく使うコマンド

| コマンド | 説明 |
|---------|------|
| `./gradlew :app-android:assembleDebug` | Android APKビルド |
| `./gradlew :shared:linkDebugFrameworkIosSimulatorArm64` | iOS Simulator向けビルド |

### 重要ファイル参照

| 内容 | 参照先 |
|------|--------|
| Presenter実装例 | `feature/settings/src/commonMain/.../SettingsPresenter.kt` |
| UI実装例 | `feature/settings/src/commonMain/.../Settings.kt` |
| UseCase実装例 | `core/domain/src/commonMain/.../GetThemeUseCase.kt` |
| DI設定 (共通) | `shared/src/commonMain/.../di/AppGraph.kt` |
| DI設定 (Android) | `shared/src/androidMain/.../di/AndroidAppGraph.kt` |
| DI設定 (iOS) | `shared/src/iosMain/.../di/IosAppGraph.kt` |
| バージョン情報 | `gradle/libs.versions.toml` |
| Convention Plugins | `build-logic/convention/src/main/kotlin/*.gradle.kts` |

### Circuitパターン構成

```
Screen (ナビゲーションキー) → Presenter (@Composable present() → State) → UI (@CircuitInject)
```