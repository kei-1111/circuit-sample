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

### よくある間違い

- ❌ UIでビジネスロジック → ✅ Presenterで処理
- ❌ Stateを直接変更 → ✅ eventSinkでイベント発行
- ❌ Presenterで`repository.xxx()`呼び出し → ✅ UseCase経由

## HOW: 開発方法

### よく使うコマンド

| コマンド | 説明 |
|---------|------|
| `./gradlew assembleDebug` | Android APKビルド |
| `./gradlew compileKotlinIosArm64` | iOS向けビルド |

### 重要ファイル参照

| 内容 | 参照先 |
|------|--------|
| Presenter実装例 | `composeApp/.../feature/settings/SettingsPresenter.kt:54` |
| UI実装例 | `composeApp/.../feature/settings/Settings.kt:38` |
| UseCase実装例 | `composeApp/.../core/domain/GetThemeUseCase.kt:8` |
| DI設定 | `composeApp/.../di/AppGraph.kt` |
| バージョン情報 | `gradle/libs.versions.toml` |

### Circuitパターン構成

```
Screen (ナビゲーションキー) → Presenter (@Composable present() → State) → UI (@CircuitInject)
```