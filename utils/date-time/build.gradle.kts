plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}
android {
    namespace = "com.grappim.date_time"
}

dependencies {
    implementation(project(":common:di"))
    implementation(libs.kotlinx.datetime)
}
