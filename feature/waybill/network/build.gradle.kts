plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.cashier.feature.waybill.network"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":utils:date-time"))
    implementation(project(":utils:logger"))

    implementation(project(":common:di"))
    implementation(project(":common:annotations"))
    implementation(project(":common:network:serializers"))

    implementation(project(":feature:waybill:domain"))

    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
    implementation(libs.androidx.paging.common)
}
