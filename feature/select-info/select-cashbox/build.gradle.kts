plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.cashier.cashbox"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":navigation"))
    implementation(project(":domain"))
    implementation(project(":uikit"))

    implementation(project(":common:di"))
    implementation(project(":common:asynchronous"))
    implementation(project(":common:lce"))

    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))

    implementation(project(":feature:select-info:common-navigation"))

    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.material)

    implementation(libs.coil)

    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.fragment)
}
