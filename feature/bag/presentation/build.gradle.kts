plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
    alias(libs.plugins.cashier.android.library.compose)
}

android {
    namespace = "com.grappim.feature.bag.presentation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":navigation"))
    implementation(project(":uikit"))
    implementation(project(":common:async"))

    implementation(project(":common:di"))
    implementation(project(":common:lce"))

    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))

    implementation(project(":feature:bag:domain"))
    implementation(project(":feature:bag:network"))
    implementation(project(":feature:bag:repository"))

    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.androidx.fragment)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.cicerone)
}
