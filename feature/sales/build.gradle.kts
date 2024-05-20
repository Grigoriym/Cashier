plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.sales"
}

dependencies {
    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))

    implementation(project(":feature:products:domain"))
    implementation(project(":feature:bag:domain"))

    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":navigation"))
    implementation(project(":uikit"))

    implementation(project(":common:lce"))
    implementation(project(":common:di"))
    implementation(project(":common:async"))

    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.cicerone)
}
