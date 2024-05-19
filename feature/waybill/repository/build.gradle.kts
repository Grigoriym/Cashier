plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.feature.waybill.repository"
}

dependencies {
    implementation(project(":common:di"))
    implementation(project(":common:lce"))
    implementation(project(":common:asynchronous"))

    implementation(project(":utils:date-time"))
    implementation(project(":utils:logger"))
    implementation(project(":domain"))

    implementation(project(":feature:waybill:network"))
    implementation(project(":feature:waybill:domain"))

    implementation(libs.androidx.paging.common)
}
