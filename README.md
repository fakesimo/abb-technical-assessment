# ABB Technical Assessment

Android app (Jetpack Compose, Material 3) that lists the user's GitHub repositories, shows their details, and has an AI assistant that answers questions about them.

# Setup

Add the two secrets to `local.properties` (git-ignored), or set the env vars `GITHUB_TOKEN` and `GEMINI_KEY`:

```properties
github.token=REPLACE_WITH_YOUR_PAT
gemini.key=REPLACE_WITH_YOUR_GEMINI_API_KEY
```

- **GitHub token:** Settings → Developer settings → Personal access tokens. A fine-grained, read-only token is enough.
- **Gemini key:** create one in [Google AI Studio](https://aistudio.google.com/) (free tier, no credit card). The free quota is small and the model is sometimes slow or overloaded.

Sync Gradle and rebuild after changing them. Both values are compiled into the APK through `BuildConfig`, which is fine for this assessment but not for production.

# Architecture

- **MVI / unidirectional data flow:** each screen has a `State`, an `Action` and a ViewModel exposing `StateFlow`. One-off effects (snackbar, navigation) use a `SharedFlow`. Stateful and stateless composables are separated.
- **Koin** for dependency injection: lightweight, no code generation, KMP-ready. One ViewModel per navigation destination.
- **Type-safe Navigation Compose** with `@Serializable` routes.
- **Ports and adapters:** ViewModels depend on interfaces (`GitRepoRepository`, `AssistantRepository`). Ktor calls, DTOs and mappers stay in a `remote` package, so API details never reach the UI.
- **Ktor client** (shared, OkHttp engine) with kotlinx.serialization. Only the first 50 repositories are fetched; pagination is not handled.
- **AI assistant:** a bottom sheet that calls the Gemini Interactions API over REST with function calling. Tools (`AssistantTool`) wrap the same repositories as the UI, and `RealAssistantRepository` runs the tool loop until the model answers. REST was chosen because it matches the assignment and needs no extra plugins or Firebase project.

Not done yet: typed error messages, Coil, tests.
