plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.stock"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":navigation"))
    implementation(project(":uikit"))
    implementation(project(":domain"))

    implementation(project(":common:lce"))
    implementation(project(":common:di"))
    implementation(project(":common:async"))

    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))

    implementation(project(":feature:select-info:common-navigation"))

    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.coil)

    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.fragment)

    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.core)
}
