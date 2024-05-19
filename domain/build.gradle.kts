plugins {
    alias(libs.plugins.cashier.java.library)
}

dependencies {
    implementation(project(":utils:logger"))
    implementation(project(":utils:calculations"))
    implementation(project(":common:di"))
    implementation(project(":common:lce"))
    implementation(project(":common:asynchronous"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.common)
}
