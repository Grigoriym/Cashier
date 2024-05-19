plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.grappim.feature.auth.data_network"
}

dependencies {
    implementation(project(":common:di"))

    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization)
}
