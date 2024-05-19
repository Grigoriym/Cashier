plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.sign_up_presentation"
}

dependencies {
    implementation(project(":feature:sign-up:domain"))
    implementation(project(":feature:sign-up:repository"))
    implementation(project(":feature:auth:network"))
    implementation(project(":data:network"))
    implementation(project(":core"))
    implementation(project(":navigation"))
    implementation(project(":uikit"))
    implementation(project(":domain"))
    implementation(project(":common:lce"))
    implementation(project(":common:di"))
    implementation(project(":common:asynchronous"))

    implementation(libs.retrofit)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.livedata)
}
