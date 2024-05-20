plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
}

android {
    namespace = "com.grappim.uikit"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":utils:calculations"))
    implementation(project(":feature:waybill:domain"))

    implementation(libs.google.material)
    implementation(libs.zxing) {
        isTransitive = false
    }
    implementation(libs.google.zxing)

    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.swiperefresh)
    implementation(libs.circularProgressBar)
    implementation(libs.viewBinding)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.constraintlayout.compose)
}
