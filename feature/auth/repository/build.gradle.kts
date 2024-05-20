plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.feature.auth.data_repository"
}

dependencies {
    implementation(project(":common:di"))
    implementation(project(":common:db"))
    implementation(project(":common:lce"))
    implementation(project(":common:async"))
    implementation(project(":data:repository-api"))
    implementation(project(":utils:logger"))

    implementation(project(":domain"))

    implementation(project(":feature:auth:domain"))
    implementation(project(":feature:auth:network"))

    implementation(libs.kotlinx.coroutines.core)
}
