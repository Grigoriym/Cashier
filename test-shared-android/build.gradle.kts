plugins {
    alias(libs.plugins.cashier.android.library)
}

android {
    namespace = "com.grappim.cashier.testsharedandroid"
}

dependencies {
    implementation(libs.androidx.lifecycle.livedata)

    api(libs.androidx.arch.core.testing)
    api(libs.androidx.test.core)
}
