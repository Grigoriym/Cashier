plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.cashier.feature.productcategory.network"
}

dependencies {
    implementation(project(":common:annotations"))

    implementation(libs.kotlinx.serialization)

    implementation(libs.retrofit)
}
