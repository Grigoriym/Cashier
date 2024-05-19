plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.grappim.feature.bag.network"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common:di"))
    implementation(project(":common:annotations"))
    implementation(project(":common:network:serializers"))

    implementation(project(":feature:bag:domain"))
    implementation(project(":feature:bag:db"))

    implementation(libs.kotlinx.serialization)

    implementation(libs.retrofit)
    implementation(libs.androidx.paging.common)
}
