plugins {
    alias(libs.plugins.cashier.java.library)
}

dependencies {
    implementation(project(":utils:logger"))
    implementation(project(":common:di"))
    implementation(project(":common:lce"))
    implementation(project(":common:async"))

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.androidx.paging.common)
}
