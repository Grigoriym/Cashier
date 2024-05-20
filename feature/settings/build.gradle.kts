plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.feature.settings"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":uikit"))
    implementation(project(":navigation"))

    implementation(project(":utils:extensions"))
    implementation(project(":common:async"))

    implementation(project(":common:di"))

    implementation(libs.androidx.lifecycle.viewmodel)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui)

    implementation(libs.androidx.fragment)
    implementation(libs.cicerone)
}
