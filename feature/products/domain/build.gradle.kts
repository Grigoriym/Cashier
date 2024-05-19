plugins {
    alias(libs.plugins.cashier.java.library)
}

dependencies {
    implementation(project(":utils:logger"))
    implementation(project(":utils:calculations"))
    implementation(project(":common:lce"))

    implementation(project(":domain"))
    implementation(project(":feature:product-category:domain"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.common)
}
