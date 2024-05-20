plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.workers"
}

dependencies {
    implementation(project(":domain"))

    implementation(project(":data:network"))
    implementation(project(":data:db"))
    implementation(project(":data:workers-api"))

    implementation(project(":utils:logger"))

    implementation(project(":common:di"))
    implementation(project(":common:db"))
    implementation(project(":common:async"))

    implementation(project(":feature:product-category:db"))
    implementation(project(":feature:product-category:domain"))
    implementation(project(":feature:products:domain"))

    implementation(project(":feature:auth:network"))

    implementation(libs.androidx.work.runtime)
}
