plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.feature.waybill.presentation"
}

dependencies {
    implementation(project(":common:di"))
    implementation(project(":common:asynchronous"))
    implementation(project(":common:lce"))

    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":navigation"))
    implementation(project(":uikit"))

    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))
    implementation(project(":utils:extensions"))
    implementation(project(":utils:logger"))

    implementation(project(":feature:waybill:domain"))
    implementation(project(":feature:waybill:network"))
    implementation(project(":feature:waybill:repository"))

    implementation(project(":feature:products:domain"))

    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.zxing) {
        isTransitive = false
    }
    implementation(libs.google.zxing)
    implementation(libs.viewBinding)
    implementation(libs.coil)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.viewBinding)
    implementation(libs.cicerone)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.constraintlayout.compose)
}
