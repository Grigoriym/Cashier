plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.utils.biometric"
}

dependencies {
    implementation(project(":common:di"))
    implementation(project(":utils:logger"))
    implementation(project(":uikit"))

    implementation(libs.androidx.biometric)
    implementation(libs.androidx.appcompat)
}
