plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.library.compose)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.product_category.presentation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":navigation"))
    implementation(project(":uikit"))

    implementation(project(":common:di"))
    implementation(project(":common:asynchronous"))
    implementation(project(":common:lce"))

    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))

    implementation(project(":feature:product-category:domain"))

    implementation(libs.combineTupleFlow)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.cicerone)
}
