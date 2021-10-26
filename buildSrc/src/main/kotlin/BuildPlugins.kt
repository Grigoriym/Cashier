object BuildPlugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.core}"
    const val kotlinSerialization =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.core}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Google.hilt}"
    const val safeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.AndroidX.navigation}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}"
    const val gradleVersions =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}"
}
