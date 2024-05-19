plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.core"
}

dependencies {
    implementation(project(":uikit"))
    implementation(project(":domain"))
    implementation(project(":utils:extensions"))
    implementation(project(":utils:logger"))
    implementation(project(":navigation"))
    implementation(project(":common:di"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment)
    implementation(libs.cicerone)
    implementation(libs.androidx.lifecycle.livedata)
}
