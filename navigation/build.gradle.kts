plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.navigation"
}

dependencies {
    implementation(project(":uikit"))
    implementation(project(":common:di"))

    implementation(libs.cicerone)
    implementation(libs.androidx.fragment)
}
