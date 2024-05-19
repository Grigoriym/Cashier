plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.menu"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":uikit"))
    implementation(project(":domain"))
    implementation(project(":navigation"))
    implementation(project(":common:asynchronous"))
    implementation(project(":common:di"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.runtime)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.cicerone)
}
