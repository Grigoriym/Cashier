plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}
android {
    namespace = "com.grappim.cashier.datetime"
}

dependencies {
    implementation(project(":common:di"))
    implementation(libs.kotlinx.datetime)
}
