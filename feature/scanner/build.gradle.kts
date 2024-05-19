plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.scanner"
}

dependencies {
    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))
    implementation(project(":utils:logger"))
    implementation(project(":utils:extensions"))
    implementation(project(":core"))
    implementation(project(":uikit"))
    implementation(project(":navigation"))
    implementation(project(":common:di"))

    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.androidx.fragment)

    implementation(libs.viewBinding)
    implementation(libs.coil)
    implementation(libs.recyclerViewAnimators)
    implementation(libs.combineTupleLiveData)

    implementation(libs.zxing) {
        isTransitive = false
    }
    implementation(libs.google.zxing)
}
