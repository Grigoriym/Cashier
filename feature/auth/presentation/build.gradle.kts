plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.feature.auth.presentation"
}

dependencies {
    implementation(project(":feature:auth:domain"))
    implementation(project(":feature:auth:repository"))
    implementation(project(":feature:auth:network"))
    implementation(project(":data:workers-api"))
    implementation(project(":utils:biometric"))
    implementation(project(":data:repository-api"))
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":uikit"))
    implementation(project(":utils:logger"))
    implementation(project(":navigation"))
    implementation(project(":common:lce"))
    implementation(project(":common:di"))
    implementation(project(":common:async"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment)

    implementation(libs.androidx.biometric)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(libs.retrofit)
    implementation(libs.cicerone)
}
