plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.sign_up_repository"
}

dependencies {
    implementation(project(":feature:sign-up:domain"))

    implementation(project(":feature:auth:network"))

    implementation(project(":domain"))

    implementation(project(":common:di"))
    implementation(project(":common:lce"))
    implementation(project(":common:async"))
}
