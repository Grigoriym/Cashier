plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {

    namespace = "com.grappim.products.network"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:db"))

    implementation(project(":common:di"))
    implementation(project(":common:annotations"))
    implementation(project(":common:network:serializers"))

    implementation(project(":feature:bag:domain"))
    implementation(project(":feature:bag:network"))
    implementation(project(":feature:bag:db"))

    implementation(project(":utils:logger"))

    implementation(libs.kotlinx.serialization)

    implementation(libs.retrofit)

    implementation(libs.androidx.paging.common)
}
