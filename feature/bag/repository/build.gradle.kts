plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.cashier.feature.bag.repository"
}

dependencies {
    implementation(project(":domain"))

    implementation(project(":common:di"))
    implementation(project(":common:lce"))
    implementation(project(":common:asynchronous"))

    implementation(project(":utils:date-time"))
    implementation(project(":utils:logger"))

    implementation(project(":feature:bag:network"))
    implementation(project(":feature:bag:domain"))
    implementation(project(":feature:bag:db"))

    implementation(libs.kotlinx.coroutines.core)
}
