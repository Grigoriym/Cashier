plugins {
    alias(libs.plugins.cashier.android.library)
}

dependencies {
    implementation(libs.androidx.lifecycle.livedata)
}

android {
    namespace = "com.grappim.cashier.test_shared_android"
}
