plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.feature.select_info.root_presentation"
}

dependencies {
    implementation(project(":feature:select-info:select-cashbox"))
    implementation(project(":feature:select-info:select-stock"))
    implementation(project(":feature:select-info:common-navigation"))

    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":navigation"))

    implementation(project(":common:di"))
    implementation(project(":utils:logger"))

    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.fragment)
}
