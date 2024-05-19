plugins {
alias(libs.plugins.cashier.java.library)
}

dependencies {
    implementation(project(":common:lce"))
    implementation(project(":utils:calculations"))
    implementation(project(":domain"))

    implementation(libs.kotlinx.coroutines.core)
}
