plugins {
    alias(libs.plugins.cashier.java.library)
}

dependencies {
    implementation(project(":common:lce"))
    implementation(libs.kotlinx.coroutines.core)
}
