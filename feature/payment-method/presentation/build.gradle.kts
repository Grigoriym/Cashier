plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.cashier.feature.paymentmethod.presentation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":navigation"))
    implementation(project(":uikit"))

    implementation(project(":common:lce"))
    implementation(project(":common:async"))
    implementation(project(":common:di"))

    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))
    implementation(project(":utils:logger"))

    implementation(project(":feature:payment-method:domain"))
    implementation(project(":feature:bag:domain"))

    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.androidx.fragment)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.cicerone)
}
