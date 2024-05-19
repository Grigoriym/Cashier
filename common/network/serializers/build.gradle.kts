plugins {
    alias(libs.plugins.cashier.java.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization)
}
