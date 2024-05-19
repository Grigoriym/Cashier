plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
    alias(libs.plugins.cashier.android.library.compose)
}

android {
    namespace = "com.grappim.products.presentation"
}

dependencies {
    implementation(project(":core"))

    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))

    implementation(project(":feature:product-category:domain"))
    implementation(project(":feature:products:domain"))
    implementation(project(":feature:products:network"))
    implementation(project(":feature:products:repository"))

    implementation(project(":domain"))
    implementation(project(":uikit"))
    implementation(project(":navigation"))

    implementation(project(":common:di"))
    implementation(project(":common:asynchronous"))
    implementation(project(":common:lce"))

    implementation(libs.combineTupleFlow)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.fragment)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.cicerone)
}
